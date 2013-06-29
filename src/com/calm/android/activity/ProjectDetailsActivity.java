package com.calm.android.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
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
    private TextView mSubject;
    private TextView mLevel;
    private TextView mBudget;

    Context mContext = this;

    private Projectendpoint service;
    String mProjectId;
    ProgressDialog pd;

    private Project mProject;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        mLanguage = (TextView) findViewById(R.id.projectscreen_language_to_set__textview);
        mSubject = (TextView) findViewById(R.id.projectscreen_subject_to_set_textview);
        mLevel = (TextView) findViewById(R.id.projectscreen_level_textview);
        mBudget = (TextView) findViewById(R.id.projectscreen_budget_textview);


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
        pd = ProgressDialog.show(this,"","loading");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{

                    mProject = projectEndpoint.getProject(mProjectId).execute();

                } catch (Exception e){
                    System.out.println("exp********");
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


    private static final int UPDATE_IMAGE = 1 ;
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==UPDATE_IMAGE){
                dismissProgressDialog();
              //  String detailsStr = new String();
                mLanguage.setText(mProject.getLanguage());
                mSubject.setText(mProject.getSubject());
                mLevel.setText(mProject.getLevel().toString());
                mBudget.setText("Project's Budget: " + String.valueOf(mProject.getBudget()));

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
