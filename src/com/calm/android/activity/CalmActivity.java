package com.calm.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.calm.android.R;
import com.calm.android.SplashActivity;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import net.simonvt.menudrawer.MenuDrawer;
import roboguice.inject.InjectView;

public abstract class CalmActivity extends RoboSherlockFragmentActivity {

    private ActionBar actionBar;
    private MenuDrawer mMenuDrawer;


    //@InjectView(R.id.sidemenu_logout)
    private Button mLogoutButton;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        actionBarSetup();
        menuDrawerSetup();



        
    }

    private void actionBarSetup() {
        actionBar = getSupportActionBar();
    }

    private void menuDrawerSetup() {
        int layoutId = getLayoutId();
        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setContentView(layoutId);
        mMenuDrawer.setMenuView(R.layout.side_menu);

        mLogoutButton = (Button) findViewById(R.id.sidemenu_logout);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logout();
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);


            }
        });


    }

    protected abstract int getLayoutId();

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_student:
                Toast.makeText(this, "Student", Toast.LENGTH_SHORT);
                System.out.println("Student");
                return true;
            case R.id.menu_techer:
                Toast.makeText(this, "Teacher", Toast.LENGTH_SHORT);
                System.out.println("Teacher");
                return true;
        }

        return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void logout(){
        SharedPreferences settings = getSharedPreferences("CALM",0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("userName", null);

        editor.commit();

    }
}
