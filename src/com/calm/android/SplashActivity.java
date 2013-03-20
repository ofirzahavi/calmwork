package com.calm.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.calm.android.activity.CalmActivity;
import com.calm.android.activity.LoginActivity;
import com.calm.android.activity.UserWorksActivity;
import net.simonvt.menudrawer.MenuDrawer;

public class SplashActivity extends CalmActivity {

    private ActionBar actionBar;
    private MenuDrawer mMenuDrawer;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        SharedPreferences settings = getSharedPreferences("CALM", 0);
        String userName = settings.getString("userName", null);
        Intent intent;
        if (userName == null){
             intent = new Intent(getApplicationContext(), LoginActivity.class);
             startActivity(intent);
        } else {
            intent = new Intent(getApplicationContext(), UserWorksActivity.class);
            startActivity(intent);

        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.main;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
