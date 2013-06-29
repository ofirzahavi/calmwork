package com.calm.android.activity;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.Button;
import com.calm.android.R;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calmuserendpoint.Calmuserendpoint;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.Projectendpoint;
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
    private static final String WEB_CLIENT_ID = "1080189142533-1hin5t3rbq6655cop98oam64n9q9kbni.apps.googleusercontent.com";

    private SharedPreferences settings;
    private String accountNaStringme;
    private Projectendpoint projectService;
    private Calmuserendpoint userService;
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
    ProgressDialog pd;

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

          //      editor.putString("userName", mEmailText.getText().toString());
          //      editor.putString("userPassword", mPasswordText.getText().toString());
                editor.commit();
                chooseAccount();

              //  Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
              //  startActivity(intent);

            }
        });


      /*  mSignupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);


            }
        });  */

     //   Account[] accounts = AccountManager.get(this).getAccounts();
        /*for (Account a:accounts){
            System.out.println(a.name);
        }  */
       // ac = accounts[0];

    }

    static final int REQUEST_ACCOUNT_PICKER = 2;

    void chooseAccount() {
        //Intent i = AccountManager.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
        //startActivityForResult(i, REQUEST_ACCOUNT_PICKER);

        String scope = "biuninja2013.appspot.com";
        String USERINFO_SCOPE =
                "oauth2:https://www.googleapis.com/auth/userinfo.profile";
     //   credential = new GoogleAccountCredential(mContext,USERINFO_SCOPE );
        CalmActivity.credential = GoogleAccountCredential.usingAudience(mContext,"server:client_id:" + WEB_CLIENT_ID);

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

                        CalmActivity.credential.setSelectedAccountName(accountName) ;
                        Projectendpoint.Builder builder = new Projectendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), CalmActivity.credential);
                        projectService = builder.build();
                        CalmActivity.projectEndpoint = projectService.projectEndpoint();

                        Calmuserendpoint.Builder userbuilder = new Calmuserendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), CalmActivity.credential);
                        userService = userbuilder.build();
                        CalmActivity.userEndpoint = userService.calmUserEndpoint();

                      ;



                        Runnable r = new Runnable() {

                            @Override
                            public void run() {
                                try{

                                    System.out.println("******* try");
                                    CalmUser calmUser;

                                    calmUser = CalmActivity.userEndpoint.getCalmUser(CalmActivity.credential.getSelectedAccountName()).execute();

                                    if (calmUser.containsKey("error_message"))
                                    {
                                        System.out.println("**** insert user before");
                                        calmUser = new CalmUser();
                                        calmUser.setMail(CalmActivity.credential.getSelectedAccountName());
                                        System.out.println("**** insert user middle");
                                        calmUser = CalmActivity.userEndpoint.insertCalmUser(calmUser).execute();
                                        System.out.println("**** insert user after");

                                    }

                                    if (!(calmUser.containsKey("error_message")))
                                    {

                                        System.out.println("not null ******");
                                        Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                                        startActivity(intent);
                                    }

                                     //TODO: HANDLE CASE WHEN USER WAS NOT CREATED

                                } catch (Exception e){
                                    System.out.println("******* catch");
                                    e.printStackTrace();
                                }

                                finally {
                                    System.out.println("******* finally 1");
                                    String myString = new String("test");
                                    //updateTextView.obtainMessage(0, 10, 20, myString).sendToTarget();
                                    Message msg = handler.obtainMessage();
                                    msg.what = UPDATE_IMAGE;
                                    handler.sendMessage(msg);

                                }

                            }
                        };
                        Thread t = new Thread(r);
                        t.start();

                    }
                }
                break;
        }

    }

    private static final int UPDATE_IMAGE = 1;
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==UPDATE_IMAGE){
                dismissProgressDialog();

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
