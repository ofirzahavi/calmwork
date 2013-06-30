package com.calm.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.actionbarsherlock.app.ActionBar;
import com.calm.android.activity.CalmActivity;
import com.calm.android.activity.LoginActivity;

public class SplashActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        Runnable r = new Runnable() {
            @Override
            public void run(){
                SharedPreferences settings = getSharedPreferences("CALM", 0);
                String userName = settings.getString("userName", null);
                Intent intent;
                if (userName == null){
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 1000);



    }

}
