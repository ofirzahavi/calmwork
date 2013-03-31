package com.calm.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.calm.android.activity.*;
import net.simonvt.menudrawer.MenuDrawer;

public class SplashActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        SharedPreferences settings = getSharedPreferences("CALM", 0);
        String userName = settings.getString("userName", null);



        Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
        startActivity(intent);
        /*
        //Intent intent;
        if (userName == null){
             intent = new Intent(getApplicationContext(), LoginActivity.class);
             startActivity(intent);
        } else {
            intent = new Intent(getApplicationContext(), WorksListActivity.class);
            startActivity(intent);

        }
          */

    }

}
