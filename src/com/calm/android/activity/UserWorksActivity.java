package com.calm.android.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import com.calm.android.R;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/20/13
 * Time: 5:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserWorksActivity extends CalmActivity {


    @InjectView(R.id.works_user_name)
    private TextView mUserNameTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("CALM",0);
        String userName = settings.getString("userName", "Error - no user found");

        mUserNameTextView.setText(userName);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.main;  //To change body of implemented methods use File | Settings | File Templates.


    }
}