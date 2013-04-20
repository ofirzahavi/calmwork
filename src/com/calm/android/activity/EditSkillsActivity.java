package com.calm.android.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.calm.android.R;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 14/04/13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class EditSkillsActivity extends CalmActivity{

    @Override
    protected int getLayoutId() {
        return R.layout.edit_skills_screen;
    }

    @InjectView(R.id.editskills_button_add_skills)
    private Button mEditSkillsnew;

    @InjectView(R.id.editskills_button_add_language)
    private Button mEditlanguagenew;

    private Spinner mSubjectSpinnerSkills;



    final Context context = this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();

        mEditSkillsnew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_skills_dialog);
                dialog.setTitle("Add Skills:");

                dialog.show();

                Spinner spinner  =   getmSubjectSpinnerSkills() ;
                spinner = (Spinner) findViewById(R.id.editskills_spinner_subject);
                //addListenerOnSpinnerItemSelection();

            }
        });


        mEditlanguagenew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.edit_languages_dialog);
                dialog.setTitle("Add language:");

                dialog.show();
            }
        });
    }

    private Spinner getmSubjectSpinnerSkills()
    {
        return mSubjectSpinnerSkills;
    }

    private void setmSubjectSpinnerSkills(Spinner spinner)
    {
        mSubjectSpinnerSkills = spinner;
        mSubjectSpinnerSkills.setOnItemSelectedListener(new CustomOnItemSelectedListener())   ;
    }

    public void addListenerOnSpinnerItemSelection() {
        // mLevelSpinnerSkills = (Spinner) findViewById(R.id.newproject_spinner_level_skills);
        //  mLevelSpinnerSkills.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mSubjectSpinnerSkills.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }


}
