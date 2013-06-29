package com.calm.android.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.calm.android.adapter.SkillsListAdapter;
import com.google.api.services.calmuserendpoint.Calmuserendpoint;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.CollectionResponseProject;
import com.google.api.services.projectendpoint.model.Project;
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


    @InjectView(R.id.editskills_button_add_skills)
    private Button mSkillsButton;


    Context mContext = this;
    private Calmuserendpoint service;
    private CalmUser mCalmUser;
    @Override

    protected int getLayoutId() {
        return R.layout.edit_skills_screen;
    }



    private Spinner mSubjectSpinnerSkills;
    private Spinner mLevelSpinnerSkills;


    ArrayList<String> array;

    Button mAddSkillButton;


    final Context context = this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddSkillButton = (Button) findViewById(R.id.editskills_button_add_skills);
        getUser();
        SkillsListAdapter adapter = new SkillsListAdapter(mContext, mCalmUser);
        mSkillsListView.setAdapter(adapter);


        mSkillsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                System.out.println("blaaaah before");
                getUser();

                if (mCalmUser==null)
                    System.out.println("userIs null ****");
                else
                {  //   System.out.println("blaaaah");
                  //  filteredList = filterToPast();
                    SkillsListAdapter adapter = new SkillsListAdapter(mContext, mCalmUser);
                    mSkillsListView.setAdapter(adapter);
                    //  Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                    // startActivity(intent);
             //       System.out.println("skills list" + "******" + mCalmUser.getSkills().toString());
                }
            }
        });

        mAddSkillButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("******* printing get projects very before");
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try{
                            System.out.println("******* new new new oron yael po leyadewno");
                            List<String> mySkillsList = mCalmUser.getSkills();
                            System.out.println("******* printing get projects after leyadewno leyadewno");
                            if(mySkillsList==null){
                                System.out.println("******* printing yael in the if1111");
                                mySkillsList=new ArrayList<String>();
                                System.out.println("******* printing yael in the if2222");
                            }
                            if(!(mySkillsList.contains("bluhhhh"))){
                                System.out.println("******* printing yael in the if3333");
                                mySkillsList.add("bluhhhh");
                                System.out.println("******* printing yael in the if4444");
                                {
                            }
                            System.out.println("*************** yael yael"+mySkillsList.toString());
                            System.out.println("******* printing get projects after leyadewno leyadewno2");
                            mCalmUser.setSkills(mySkillsList);
                            CalmActivity.userEndpoint.updateCalmUser(mCalmUser).execute();
                            getUser();
                        }} catch (Exception e){
                            e.printStackTrace();
                            System.out.println("null pointer ****");
                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();

            }
        });

    }




    public void getUser(){

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    //System.out.println("******* printing get projects before");
                    System.out.println("get user good ****" + CalmActivity.credential.getSelectedAccountName());
                    String account =    CalmActivity.credential.getSelectedAccountName();
                    mCalmUser = userEndpoint.getCalmUser(account).execute();
                    //System.out.println("******* printing get projects middle");
                    //filteredList=projectsList;
                     System.out.println("******* printing get projects call");

                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("get user exp ****");
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

    }

}
