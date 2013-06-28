package com.calm.android.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;

import com.google.gson.Gson;
import roboguice.inject.InjectView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 01/04/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */

public class StudentHomeActivity extends CalmActivity implements CompoundButton.OnCheckedChangeListener{
    private static final int UPDATE_IMAGE = 1;

    //   @InjectView(R.id.studenthome_switch_student_or_ninja)
//    private Switch mSwitchStudentNinja;

    @InjectView(R.id.studenthome_button_work_in_progress)
    private Button mWorkInProgressButton;

    @InjectView(R.id.studenthome_button_create_new_project)
    private Button mNewProjectButton;

    @InjectView(R.id.studenthome_button_awaiting_response)
    private Button mAwaitingResponseButton;

    @InjectView(R.id.studenthome_button_past_projects)
    private Button mPastProjectsButton;

    @InjectView(R.id.studenthome_listview_worklist)
    private ListView mProjectsListView;

    Context mContext = this;
    List<Project> projectsList;
    List<Project> filteredList;
    private Projectendpoint service;
    ProgressDialog pd;

    ProjectsListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.student_home_screen;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getProjects();

        System.out.println("******** print from on create" +projectsList);
     //   filteredList = projectsList;




     //   mSwitchStudentNinja = (Switch) findViewById(R.id.studenthome_switch_student_or_ninja);
  /*
       if (mSwitchStudentNinja != null)
       {
            mSwitchStudentNinja.setOnCheckedChangeListener(this);
       }
    */



        mNewProjectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                startActivity(intent);
            }
        });

        mPastProjectsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

           //     System.out.println("blaaaah before");
           //       getProjects();
               System.out.println("past pressed *****");
                filteredList = filterToPast();
                adapter.notifyDataSetChanged();
           //     ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
          //     mProjectsListView.setAdapter(adapter);
              //  Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
               // startActivity(intent);
            }
        });

        mWorkInProgressButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //     System.out.println("blaaaah before");
                //       getProjects();
                System.out.println("work in progress pressed *****");

                filteredList = filterToPast();
//                mProjectsListView.notify();
                //     ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
                //     mProjectsListView.setAdapter(adapter);
                //  Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                // startActivity(intent);
            }
        });

        mAwaitingResponseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //     System.out.println("blaaaah before");
                //       getProjects();
                System.out.println("awaiting response pressed *****");
                filteredList = filterToAwaitingResponse();

          //      mProjectsListView.notifyAll();

                //     ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
                //     mProjectsListView.setAdapter(adapter);
                //  Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                // startActivity(intent);
            }
        });


    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, (isChecked ? "Ninja mode" : "Student mode"),
                Toast.LENGTH_SHORT).show();

        if (isChecked==true)
        {
            Intent intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
            startActivity(intent);
        }

    }

    private List<Project> filterToPast(){
        return projectsList;
    }

    private List<Project> filterToAwaitingResponse(){
        return projectsList;
    }

  /*  public void onPastProjectsClick(View v) {
      //  Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
        System.out.println("blaaaah before");
        getProjects();
        System.out.println("blaaaah");
        filteredList = filterToPast();
        ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
        mProjectsListView.setAdapter(adapter);
    }    */




    public void getProjects(){
        pd = ProgressDialog.show(this, "Updating Projects List", "loading");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("******* printing get projects before");
                    CollectionResponseProject projects = projectEndpoint.listProject().execute();
                    //System.out.println("******* printing get projects middle");
                    filteredList = projects.getItems();
                    Gson gson = new Gson();
                    String x = gson.toJson(filteredList);
                    System.out.println("******* gson list" + x);

               //     mProjectsListView.notify();
                //    mProjectsListView.notifyAll();
                    //filteredList=projectsList;
                    System.out.println("******* try");


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



    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==UPDATE_IMAGE){
                dismissProgressDialog();
                adapter = new ProjectsListAdapter(mContext, filteredList);
                mProjectsListView.setAdapter(adapter);
            //    adapter.notifyDataSetChanged();
           //     mProjectsListView.invalidateViews();
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