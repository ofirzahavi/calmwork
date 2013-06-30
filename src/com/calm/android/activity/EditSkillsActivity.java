package com.calm.android.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.adapter.LanguagesListAdapter;
import com.calm.android.adapter.ProjectsListAdapter;
import com.calm.android.adapter.SkillsListAdapter;
import com.calm.android.entities.Skill;
import com.google.api.services.calmuserendpoint.Calmuserendpoint;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.calmuserendpoint.model.CollectionResponseCalmUser;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;
import com.google.gson.Gson;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 14/04/13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class EditSkillsActivity extends CalmActivity{


    @InjectView(R.id.skills_list)
    private ListView mSkillsListView;

    @InjectView(R.id.language_list)
    private ListView mLanguagesList;


    private Button mAddLanguageButton;


    Context mContext = this;
    private Calmuserendpoint service;
    private CalmUser mCalmUser;
    @Override

    protected int getLayoutId() {
        return R.layout.edit_skills_screen;
    }


    private Spinner mSubjectSpinner;
    private Spinner mLevelSpinner;
    private Spinner mLanguageSpinner;

    ProgressDialog pd;
    SkillsListAdapter adapter;
    LanguagesListAdapter languagesAdapter;
    List<String> skillsList;

    ArrayList<String> array;

    Button mAddSkillButton;

    Skill mSkill;

   // boolean mUpToBool;

    CheckBox cbx;

    final Context context = this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  mUpToBool=false;


        mSubjectSpinner = (Spinner) findViewById(R.id.editskills_spinner_subject);
        mLevelSpinner = (Spinner) findViewById(R.id.editskills_spinner_level);
        mLanguageSpinner = (Spinner) findViewById(R.id.editskills_spinner_language);

        addListenerOnCheckbox();

        addListenerOnSpinnerItemSelection();
        mAddSkillButton = (Button) findViewById(R.id.editskills_button_add_skills);
        mAddLanguageButton = (Button) findViewById(R.id.editskillsscreen_add_languages_button);
        getUser();
        SkillsListAdapter adapter = new SkillsListAdapter(mContext, mCalmUser);
        mSkillsListView.setAdapter(adapter);


        mAddLanguageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pd = ProgressDialog.show(mContext, "Updating Languages List", "loading");

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            List<String> myLanguagesList = mCalmUser.getLanguages();
                            if(myLanguagesList==null){
                                myLanguagesList=new ArrayList<String>();
                            }
                            if(!(myLanguagesList.contains(mLanguageSpinner.getSelectedItem().toString())))
                            {
                                myLanguagesList.add(mLanguageSpinner.getSelectedItem().toString());
                                mCalmUser.setLanguages(myLanguagesList);
                                CalmActivity.userEndpoint.updateCalmUser(mCalmUser).execute();
                                getUser();
                            }}
                        catch (Exception e){
                            e.printStackTrace();

                        }

                        finally {
                            System.out.println("******* finally 1");
                            String myString = new String("test");
                            //updateTextView.obtainMessage(0, 10, 20, myString).sendToTarget();
                            Message msg = handler.obtainMessage();
                            msg.what = UPDATE_LANGUAGES_LIST;
                            handler.sendMessage(msg);

                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();

            }
        });

        mAddSkillButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pd = ProgressDialog.show(mContext, "Updating Skills List", "loading");

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            List<String> mySkillsList = mCalmUser.getSkills();
                            if(mySkillsList==null){
                                mySkillsList=new ArrayList<String>();
                            }

                            String skillString;
                            String subjectString;
                            ArrayList<Integer> levelsInteger = new ArrayList<Integer>();

                            subjectString = mSubjectSpinner.getSelectedItem().toString();
                            int j;
                            if(cbx.isChecked() == true){
                               //boolean booli = cbx.isChecked();
                               for( int i = 1;i<=mLevelSpinner.getSelectedItemPosition();i++){

                                   levelsInteger.add(i);
                               }


                            }
                            else{
                                levelsInteger.add(mLevelSpinner.getSelectedItemPosition());
                            }

                            mSkill = new Skill(subjectString , levelsInteger);
                            skillString = mSkill.toString();


                            if(!(mySkillsList.contains(skillString))){
                                mySkillsList.add(skillString);
                                {
                            }
                            mCalmUser.setSkills(mySkillsList);
                            CalmActivity.userEndpoint.updateCalmUser(mCalmUser).execute();
                            getUser();
                        }} catch (Exception e){
                            e.printStackTrace();

                        }

                        finally {
                            System.out.println("******* finally 1");
                            String myString = new String("test");
                            //updateTextView.obtainMessage(0, 10, 20, myString).sendToTarget();
                            Message msg = handler.obtainMessage();
                            msg.what = UNCHECK_CHECKBOX;
                            handler.sendMessage(msg);

                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();

            }
        });

    }





    public void getUser(){
        pd = ProgressDialog.show(this, "Updating Lists", "loading");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("******* printing get projects before");
                    mCalmUser = CalmActivity.userEndpoint.getCalmUser(CalmActivity.credential.getSelectedAccountName()).execute();
                    //System.out.println("******* printing get projects middle");
                    skillsList = mCalmUser.getSkills();
                    Gson gson = new Gson();
                    String x = gson.toJson(skillsList);
                    System.out.println("******* skillsList list" + x);

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


    private static final int UPDATE_IMAGE = 1;
    private static final int UNCHECK_CHECKBOX = 2 ;
    private static final int UPDATE_LANGUAGES_LIST =3;

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==UPDATE_IMAGE){
                dismissProgressDialog();
                adapter = new SkillsListAdapter(mContext, mCalmUser);
                mSkillsListView.setAdapter(adapter);
                languagesAdapter = new LanguagesListAdapter(mContext, mCalmUser);
                mLanguagesList.setAdapter(languagesAdapter);
                //    adapter.notifyDataSetChanged();
                //     mProjectsListView.invalidateViews();
            }

            if(msg.what==UNCHECK_CHECKBOX)
            {
                dismissProgressDialog();
                cbx.setChecked(false);
                adapter = new SkillsListAdapter(mContext, mCalmUser);
                mSkillsListView.setAdapter(adapter);
            }

            if(msg.what==UPDATE_LANGUAGES_LIST)
            {
                dismissProgressDialog();
                languagesAdapter = new LanguagesListAdapter(mContext, mCalmUser);
                mLanguagesList.setAdapter(languagesAdapter);
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


    public void addListenerOnSpinnerItemSelection() {
        mLevelSpinner = (Spinner) findViewById(R.id.editskills_spinner_level);
        mLevelSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        //  mNewProject.setLevel(mLevelSpinner.getSelectedItemPosition()); //TODO
        mSubjectSpinner = (Spinner) findViewById(R.id.editskills_spinner_subject);
        mSubjectSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mLanguageSpinner = (Spinner) findViewById(R.id.editskills_spinner_language);
        mLanguageSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    public void addListenerOnCheckbox() {

        cbx = (CheckBox) findViewById(R.id.editskills_checkbox);

        cbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {


                }

            }
        });

    }

}
