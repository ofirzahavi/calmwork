package com.calm.android.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.calm.android.model.Project;
import roboguice.inject.InjectView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 01/04/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */

public class StudentHomeActivity extends CalmActivity implements CompoundButton.OnCheckedChangeListener{

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
    ArrayList<Project> projectsList = new ArrayList<Project>();
    ArrayList<Project> filteredList;
    @Override
    protected int getLayoutId() {
        return R.layout.student_home_screen;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Project p = new Project("English assignment");
        Project p2 = new Project("two");
        Project p3 = new Project("three");
        Project p4 = new Project("4");
        Project p5 = new Project("English assignment");
        Project p6 = new Project("two");
        Project p7 = new Project("three");
        Project p8 = new Project("4");
        projectsList.add(p);
        projectsList.add(p2);
        projectsList.add(p3);
        projectsList.add(p4);
        projectsList.add(p5);
        projectsList.add(p6);
        projectsList.add(p7);
        projectsList.add(p8);

        filteredList = projectsList;
        ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
        mProjectsListView.setAdapter(adapter);




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

    private ArrayList<Project> filterToPast(){
        ArrayList<Project> list = new ArrayList<Project>();
        Project p = new Project("a past project");
        list.add(p);
        return list;
    }

    private ArrayList<Project> filterToAwaitingResponse(){
        ArrayList<Project> list = new ArrayList<Project>();
        Project p = new Project("project awaiting response");
        list.add(p);
        return list;
    }

    public void onPastProjectsClick(View v) {
      //  Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
        filteredList = filterToPast();
        ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
        mProjectsListView.setAdapter(adapter);
    }

    public void onAwaitingResponseProjectsClick(View v) {
        //Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
        filteredList = filterToAwaitingResponse();
        ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, filteredList);
        mProjectsListView.setAdapter(adapter);
    }

    public void onWorkInProgressProjectsClick(View v) {
        //  Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
        //filteredList = filterToPast();
        ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, projectsList);
        mProjectsListView.setAdapter(adapter);
    }

}
