package com.calm.android.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.calm.android.util.Utils;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calmuserendpoint.Calmuserendpoint;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;
import com.google.gson.Gson;
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
    private static final String SERVER_CLIENT_ID = "1080189142533.apps.googleusercontent.com";


    private SharedPreferences settings;
    private String accountNaStringme;
    //private Projectendpoint projectService;
    //private Calmuserendpoint userService;
    Context mContext = this;

 //   @InjectView(R.id.login_email)
 //   private EditText mEmailText;

 //   @InjectView(R.id.login_password)
 //   private EditText mPasswordText;

    @InjectView(R.id.login_button_login)
    private Button mLoginButton;

 //   @InjectView(R.id.login_button_signup)
 //   private Button mSignupButton;
 //   private Account ac;
    protected ProgressDialog pd;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        CalmActivity.credential = GoogleAccountCredential.usingAudience(mContext,"server:client_id:" + CalmActivity.WEB_CLIENT_ID);

        SharedPreferences preferences = getSharedPreferences(Utils.PREFERENCES, 0);
        String accountName = preferences.getString(Utils.ACCOUNT_NAME, null);
        if (accountName != null){
            CalmActivity.setEndpoints(accountName);
            Intent intent = new Intent(mContext, StudentHomeActivity.class);
            startActivity(intent);

        }

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.commit();
                chooseAccount();

            }
        });


    }

    static final int REQUEST_ACCOUNT_PICKER = 2;

    void chooseAccount() {
        //Intent i = AccountManager.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
        //startActivityForResult(i, REQUEST_ACCOUNT_PICKER);

        String scope = "biuninja2013.appspot.com";
        String USERINFO_SCOPE =
                "oauth2:https://www.googleapis.com/auth/userinfo.profile";
     //   credential = new GoogleAccountCredential(mContext,USERINFO_SCOPE );


        startActivityForResult(
                CalmActivity.credential.newChooseAccountIntent(),
            REQUEST_ACCOUNT_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        pd = ProgressDialog.show(this, "", "loading");

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ACCOUNT_PICKER:
                if (data != null && data.getExtras() != null) {
                    String accountName =
                            data.getExtras().getString(
                                    AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                      saveAccountName(accountName);
                      CalmActivity.setEndpoints(accountName);
                      Runnable getUser = CalmActivity.getUserService(handler);
                      Thread t = new Thread(getUser);
                      t.start();
                    }
                }
                break;
        }

    }

    private void saveAccountName(String accountName) {
        SharedPreferences preferences = getSharedPreferences(Utils.PREFERENCES, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Utils.ACCOUNT_NAME, accountName);
        editor.commit();

    }


    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==CalmActivity.DISMISS_PD){
                dismissProgressDialog();

            } else if(msg.what==CalmActivity.LOGIN_SUCSESS){
                Intent intent = new Intent(mContext, StudentHomeActivity.class);
                startActivity(intent);
            }
            super.handleMessage(msg);
        }
    };

    private void dismissProgressDialog(){
        if (pd != null){
            pd.dismiss();
            pd = null;
        }
    }

}
