package com.calm.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.calm.android.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/20/13
 * Time: 5:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends RoboActivity {

    @InjectView(R.id.login_email)
    private EditText mEmailText;

    @InjectView(R.id.login_password)
    private EditText mPasswordText;

    @InjectView(R.id.login_button_login)
    private Button mLoginButton;

    @InjectView(R.id.login_button_signup)
    private Button mSignupButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("userName", mEmailText.getText().toString());
                editor.putString("userPassword", mPasswordText.getText().toString());

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                startActivity(intent);


            }
        });


        mSignupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);


            }
        });
    }
}