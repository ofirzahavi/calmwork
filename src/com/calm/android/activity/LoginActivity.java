package com.calm.android.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.calm.android.R;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.Project;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/20/13
 * Time: 5:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginActivity extends RoboActivity {

    private static final String PREF_ACCOUNT_NAME = "bla";
    private GoogleAccountCredential credential;
    private SharedPreferences settings;
    private String accountName;
    private Projectendpoint service;

    @InjectView(R.id.login_email)
    private EditText mEmailText;

    @InjectView(R.id.login_password)
    private EditText mPasswordText;

    @InjectView(R.id.login_button_login)
    private Button mLoginButton;

    @InjectView(R.id.login_button_signup)
    private Button mSignupButton;
    private Account ac;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

       // credential = new GoogleAccountCredential(this, null);
        //chooseAccount();

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

        Account[] accounts = AccountManager.get(this).getAccounts();
        /*for (Account a:accounts){
            System.out.println(a.name);
        }  */
        ac = accounts[0];
        insertProject();
    }

    static final int REQUEST_ACCOUNT_PICKER = 2;

    void chooseAccount() {
        //Intent i = AccountManager.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
        //startActivityForResult(i, REQUEST_ACCOUNT_PICKER);

        startActivityForResult(
            credential.newChooseAccountIntent(),
            REQUEST_ACCOUNT_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ACCOUNT_PICKER:
                if (data != null && data.getExtras() != null) {
                    String accountName =
                            data.getExtras().getString(
                                    AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        setAccountName(accountName);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.commit();
                        // User is authorized.

                        insertProject();
                    }
                }
                break;
        }
    }

    private void setAccountName(String accountName) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_ACCOUNT_NAME, accountName);
        editor.commit();
        credential.setSelectedAccountName(accountName);
        this.accountName = accountName;
    }

    public void insertProject(){
        Projectendpoint.Builder builder = new Projectendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
        service = builder.build();

        Project p = new Project();
        p.setName("Oron is gay");
        try {
            GoogleAccountCredential gac =  new GoogleAccountCredential(this, null);
            service.projectEndpoint().insertProject(p).setOauthToken(gac.getToken()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}