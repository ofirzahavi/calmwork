package com.calm.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.calm.android.R;
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



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mSignupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String password =  mPasswordText.getText().toString();
                String passwordConfirmation =  mRePasswordText.getText().toString() ;

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST

               // Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_SHORT);

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("userEmail", mEmailText.getText().toString());
                editor.putString("userPassword", password);
                editor.putString("userName", mUserNameText.getText().toString());

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);


            }
        });
    }
}

