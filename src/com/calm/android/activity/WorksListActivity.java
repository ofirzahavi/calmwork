package com.calm.android.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.calm.android.R;
import roboguice.inject.InjectView;

import java.util.ArrayList;

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
    private ListView mWorksListView;


    ArrayList<String> works = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("CALM",0);
        String userName = settings.getString("userName", "Error - no user found");

        mUserNameTextView.setText(userName);

        getWorksList();


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, works);

        mWorksListView.setAdapter(adapter);

    }

    private void getWorksList() {
        works.add("Math home work");
        works.add("Calculus integrals");
        works.add("Computer networks http servlet");
        works.add("calculus homework, Gaussian");
        works.add("Biological computation assignment");
        works.add("Computer networks project");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.student_main;  //To change body of implemented methods use File | Settings | File Templates.
    }


}