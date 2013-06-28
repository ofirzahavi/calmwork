package com.calm.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.calm.android.R;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;
import roboguice.inject.InjectView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 27/06/13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class ProjectDetailsActivity extends CalmActivity{


    @Override
    protected int getLayoutId() {
        return R.layout.project_screen;
    }

    private TextView mLanguage;

    Context mContext = this;

    private Projectendpoint service;
    String mProjectId;


    private Project mProject;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        mLanguage = (TextView) findViewById(R.id.projectscreen_language_to_set__textview);

        if (extras != null) {
            System.out.println("project id is: *****" + extras.getString("projectId"));
            mProjectId = extras.getString("projectId");
            getProjectAndSetValues();
         // getProjectAndSetValues();
      //      System.out.println(mProject.getLanguage());
      //      mLanguage.setText(mProject.getLanguage());
        }
       // else System.out.println("**************");



    }


    public void getProjectAndSetValues(){
        System.out.println("project id=" + mProjectId);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{

                    mProject = projectEndpoint.getProject(mProjectId).execute();
                    mLanguage.setText(mProject.getLanguage());
                } catch (Exception e){
                    System.out.println("exp********");
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

    }
}
