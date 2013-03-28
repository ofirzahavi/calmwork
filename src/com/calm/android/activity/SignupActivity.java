package com.calm.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.calm.android.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 28/03/13
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */


public class SignupActivity extends RoboActivity {

    @InjectView(R.id.Signup_email)
    private EditText mEmailText;

    @InjectView(R.id.Signup_password)
    private EditText mPasswordText;

    @InjectView(R.id.Signup_rePassword)
    private EditText mRePasswordText;

    @InjectView(R.id.Signup_userName)
    private EditText mUserNameText;

    @InjectView(R.id.Signup_signupButton)
    private Button mSignupButton;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_account_screen);

        mSignupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String password =  mPasswordText.getText().toString();
                String passwordConfirmation =  mRePasswordText.getText().toString() ;

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST

                Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_SHORT);

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("userEmail", mEmailText.getText().toString());
                editor.putString("userPassword", password);
                editor.putString("userName", mUserNameText.getText().toString());

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), NewWorkActivity.class);
                startActivity(intent);


            }
        });
    }
}