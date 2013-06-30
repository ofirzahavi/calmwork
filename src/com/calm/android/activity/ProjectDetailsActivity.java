package com.calm.android.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

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


        inflateImages();
        if (userMode == STUDENT_MODE){
            inflateTeachers();
        }
    }

    private void inflateTeachers() {
        LinearLayout listView = (LinearLayout) findViewById(R.id.teachers_list);
        List<String> list = mProject.getAwaitingTeachers();
        for (String userNAme : list){
            TextView teacherView = createTeacherView(userNAme);
            listView.addView(teacherView);
        }

    }

    private void inflateImages() {
        LinearLayout listView = (LinearLayout) findViewById(R.id.files_list);
        List<String> list = mProject.getImageIds();

        for (String imageUrl : list){
            ImageView imageView = createImage(imageUrl);
            listView.addView(imageView);
        }
    }

    private ImageView createImage(String imageUrl) {
        ImageView newImageView = new ImageView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
        newImageView.setLayoutParams(lp);
        lp.setMargins(10, 0, 10, 0);
        Picasso.with(mContext).load(R.drawable.file_icon).into(newImageView);
        return newImageView;

    }

    private TextView createTeacherView(final String userName) {
        TextView newTextView = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        newTextView.setLayoutParams(lp);
        //lp.setMargins(10, 0, 10, 0);
        newTextView.setText(userName);
        newTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptTeacher(userName);
            }
        });
        return newTextView;

    }

    private void acceptTeacher(String teacherName) {
        mProject.setTeacherId(teacherName);
        buyProject(mProject);
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
                mLanguage.setText("Project's Language: " + mProject.getLanguage());
                mSubject.setText("Project's Subject: " + mProject.getSubject());
                mLevel.setText("Project's Level: " + String.valueOf(mProject.getLevel()));
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

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
            Log.i("paymentExample", "An invalid payment was submitted. Please see the docs.");
        }
    }
}


