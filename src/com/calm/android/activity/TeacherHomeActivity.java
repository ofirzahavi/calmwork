package com.calm.android.activity;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.app.Dialog;

import java.util.ArrayList;
import java.util.Calendar;

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

   // @InjectView(R.id.newproject_spinner_level_skills)
   // private Spinner mLevelSpinnerSkills;

    //@InjectView(R.id.newproject_spinner_subject_skills)
    //private Spinner mSubjectSpinnerSkills;

    @InjectView(R.id.teacherhome_listview_worklist)
    private ListView mProjectsListView;

    Context mContext = this;
    ArrayList<Project> projectsList = new ArrayList<Project>();

    final Context context = this;

    @Override
    protected int getLayoutId() {
        return R.layout.teacher_home_screen;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();

        Project p = new Project("one");
        Project p2 = new Project("two");
        Project p3 = new Project("three");
        Project p4 = new Project("4");
        projectsList.add(p);
        projectsList.add(p2);
        projectsList.add(p3);
        projectsList.add(p4);

        ProjectsListAdapter adapter = new ProjectsListAdapter(mContext, projectsList);
        mProjectsListView.setAdapter(adapter);

       //mSwitchStudentNinja = (Switch) findViewById(R.id.teacherhome_switch_student_or_ninja);

      //  if (mSwitchStudentNinja != null)
      //  {
       //     mSwitchStudentNinja.setOnCheckedChangeListener(this);
     //   }

        mEditSkills.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(getApplicationContext(), EditSkillsActivity.class);
                startActivity(intent);
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

    public void addListenerOnSpinnerItemSelection() {
      // mLevelSpinnerSkills = (Spinner) findViewById(R.id.newproject_spinner_level_skills);
      //  mLevelSpinnerSkills.setOnItemSelectedListener(new CustomOnItemSelectedListener());
       // mSubjectSpinnerSkills = (Spinner) findViewById(R.id.newproject_spinner_level_skills);
        //mSubjectSpinnerSkills.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }
}
