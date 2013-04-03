package com.calm.android.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.adapter.WorksListAdapter;
import com.calm.android.model.User;
import com.calm.android.model.Work;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/21/13
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorksListActivity extends CalmActivity {


    @InjectView(R.id.works_user_name)
    private TextView mUserNameTextView;

    @InjectView(R.id.works_list)
    private ExpandableListView mWorksListView;


   // ArrayList<String> works = new ArrayList<String>();
   ArrayList<Work> works = new ArrayList<Work>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("CALM",0);
        String userName = settings.getString("userName", "Error - no user found");

        mUserNameTextView.setText(userName);

        getWorksList();


        WorksListAdapter adapter = new WorksListAdapter(getApplicationContext(), works);

        mWorksListView.setAdapter(adapter);

    }

    private void getWorksList() {
        for (int i = 0; i < 10; i++){
            Work work = new Work();
            Random r = new Random();
            int n = r.nextInt(3);
            for (int j = 0; j < n; j++){
                work.bids.add(new User());
            }

            works.add(work);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.student_main;  //To change body of implemented methods use File | Settings | File Templates.
    }


}