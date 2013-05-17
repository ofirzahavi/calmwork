package com.calm.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.calm.android.R;
import com.calm.android.api.generic.ApiExecuter;
import com.calm.android.api.generic.ApiHandler;
import com.calm.android.api.generic.ApiRequest;
import com.calm.android.api.login.SignUp;
import com.calm.android.model.User;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 28/03/13
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */


public class SignupActivity extends CalmActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.create_new_account_screen;
    }


    Context mContext = this;

    @InjectView(R.id.signup_email)
    private EditText mEmailText;

    @InjectView(R.id.signup_password)
    private EditText mPasswordText;

    @InjectView(R.id.signup_rePassword)
    private EditText mRePasswordText;

    @InjectView(R.id.signup_userName)
    private EditText mUserNameText;

    @InjectView(R.id.signup_signupButton)
    private Button mSignupButton;


    public ApiHandler signupHandler = new ApiHandler() {
        @Override
        public void handle(String response) {
            Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
            startActivity(intent);

        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mSignupButton.setOnClickListener(new View.OnClickListener() {

            ;

            @Override
            public void onClick(View v) {

                String password =  mPasswordText.getText().toString();
                String passwordConfirmation =  mRePasswordText.getText().toString() ;

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST

               // Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_SHORT);

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                String name = mUserNameText.getText().toString();
                String email = mEmailText.getText().toString();
                editor.putString("userEmail", email);
                editor.putString("userPassword", password);
                editor.putString("userName",name );
                User user = new User(name, email, password);
                editor.commit();

                ApiRequest login = new SignUp(user);
                new ApiExecuter(mContext, signupHandler);


            }
        });
    }
}
