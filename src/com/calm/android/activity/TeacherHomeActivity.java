package com.calm.android.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.calm.android.adapter.ProjectsTeacherListAdapter;
import com.calm.android.entities.Skill;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.Projectendpoint;
//import com.google.api.services.projectendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;

import com.google.gson.Gson;
import roboguice.inject.InjectView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.app.Dialog;

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

public class TeacherHomeActivity extends CalmActivity implements CompoundButton.OnCheckedChangeListener{

    //@InjectView(R.id.teacherhome_switch_student_or_ninja)
   // private Switch mSwitchStudentNinja;

    @InjectView(R.id.teacherhome_button_work_in_progress)
    private Button mWorkInProgressButton;

    @InjectView(R.id.teacherhome_button_work_opportunities)
    private Button mWorkOpportunitiesButton;

    @InjectView(R.id.teacherhome_button_awaiting_response)
    private Button mAwaitingResponseButton;

    @InjectView(R.id.teacherthome_button_add_skills)
    private Button mEditSkills;

    @InjectView(R.id.teacherhome_listview_worklist)
    private ListView mProjectsListView;

    Context mContext = this;
    List<Project> projectsList;

    CalmUser mCalmUser;

    private Projectendpoint service;
    ProgressDialog pd;

    final Context context = this;

    ProjectsTeacherListAdapter adapter;

    CollectionResponseProject projects;

    @Override
    protected int getLayoutId() {
        return R.layout.teacher_home_screen;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mProjectsListView.setAdapter(adapter);

        getProjects();

        mEditSkills.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), EditSkillsActivity.class);
                startActivity(intent);
            }
        });

        mWorkOpportunitiesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getProjects();

                filterRelevantJobsList(projects);

                //adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, (isChecked ? "Ninja mode" : "Student mode"),
                Toast.LENGTH_SHORT).show();

        if (isChecked==false)
        {
            Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
            startActivity(intent);
        }

    }


    public void onTeacherFindNewProjectsClick(View v) {
        //  Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();

        ProjectsTeacherListAdapter adapter = new ProjectsTeacherListAdapter(mContext, projectsList);
        mProjectsListView.setAdapter(adapter);
    }

    public void onTeacherAwaitingResponseProjectsClick(View v) {
        //Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
        ProjectsTeacherListAdapter adapter = new ProjectsTeacherListAdapter(mContext, projectsList);
        mProjectsListView.setAdapter(adapter);
    }

    public void onTeacherWorkInProgressProjectsClick(View v) {
        //  Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
        //filteredList = filterToPast();
        ProjectsTeacherListAdapter adapter = new ProjectsTeacherListAdapter(mContext, projectsList);
        mProjectsListView.setAdapter(adapter);
    }


    public void getProjects(){
        pd = ProgressDialog.show(this, "Updating Projects List", "loading");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("******* printing get projects before");
                    mCalmUser = CalmActivity.userEndpoint.getCalmUser(CalmActivity.credential.getSelectedAccountName()).execute();
                    projects = projectEndpoint.noTeacherListProject().execute();
                    //System.out.println("******* printing get projects middle");
                    projectsList = projects.getItems();
                    Gson gson = new Gson();
                    String x = gson.toJson(projectsList);
                    System.out.println("******* gson list" + x);

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


    private static final int UPDATE_IMAGE = 1;
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==UPDATE_IMAGE){
                dismissProgressDialog();
                adapter = new ProjectsTeacherListAdapter(mContext, projectsList);
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


    ArrayList<Project> filterRelevantJobsList(CollectionResponseProject basicProjects)
    {

        //function to get the relevant job suggestions

        System.out.println("print********1*******");
        //defining variables
        System.out.println("print********2*******");
        ArrayList<Project> filtetedProjects = new ArrayList<Project>();
        ArrayList<Project> tempProjects =  new ArrayList<Project>();
        List<String> StringSkills;
        Skill tempSkill;
        ArrayList<Skill> userSkills =  new ArrayList<Skill>();
        ArrayList<Integer> tempLevels =  new ArrayList<Integer>();
        String projectLanguage =  "";
        List<String> teacherLanguages =  mCalmUser.getLanguages();
        List<Project> originalProjects = basicProjects.getItems();
        Project curProject = null;
        int i,j,k,l,m =0;
        System.out.println("print********3*******");
        // get a list with no teacher + relevant languages
        System.out.println("print********8*******");

        for (i = 0;i< originalProjects.size();i++){
            System.out.println("print********9*******");
            curProject = originalProjects.get(i);
            System.out.println("print********10*******");
            projectLanguage = curProject.getLanguage();
            System.out.println("print********11*******");
            for (j = 0;j< teacherLanguages.size();j++){
                System.out.println("print********12*******");
                    if(teacherLanguages.get(j).equals(projectLanguage))
                    {
                    System.out.println("print********14*******");
                    tempProjects.add(curProject);
                    System.out.println("print********15*******");
                 }
            }
        }

//get the skills from user, first as strings and then convert them to Skills
        StringSkills = mCalmUser.getSkills();
        System.out.println("print********18*******");
        for (i = 0;i< StringSkills.size();i++)
        {
            System.out.println("print********19*******");
            tempSkill =  new Gson().fromJson(StringSkills.get(i), Skill.class);
            System.out.println("print********20*******");
            userSkills.add(tempSkill);
            System.out.println("print********21*******");
        }

        System.out.println("print********22*******");
// get a list with no teacher + relevant languages + relevant skills
        for (i = 0;i< tempProjects.size();i++)
        {
        System.out.println("print********23*******");
        curProject = tempProjects.get(i);
        System.out.println("print********24*******");
            for (j = 0;j< userSkills.size();j++)
            {
                System.out.println("print********25*******");
                if(userSkills.get(j).getSubject().equals(curProject.getSubject()))
                {
                    System.out.println("print********26*******");
                    tempLevels.clear();
                    System.out.println("print********27*******");
                    tempLevels = userSkills.get(j).getLevels();
                    System.out.println("print********28*******");
                    for(k=0;k< tempLevels.size();k++)
                    {
                        System.out.println("print********29*******");
                        if(tempLevels.get(k)==curProject.getLevel())
                        {
                            System.out.println("print********30*******");
                            filtetedProjects .add(curProject);
                            System.out.println("print********31*******");
                        }
                    }
                }
            }

        System.out.println("print********32*******");
        }
        return filtetedProjects;
    }

}









