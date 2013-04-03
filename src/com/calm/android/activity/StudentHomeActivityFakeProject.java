package com.calm.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import com.calm.android.R;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 01/04/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */

public class StudentHomeActivityFakeProject extends CalmActivity implements CompoundButton.OnCheckedChangeListener{

    @InjectView(R.id.studenthome_switch_student_or_ninja_fake)
    private Switch mSwitchStudentNinja;

    @InjectView(R.id.studenthome_button_work_in_progress_fake)
    private Button mWorkInProgressButton;

    @InjectView(R.id.studenthome_button_create_new_project_fake)
    private Button mNewProjectButton;

    @InjectView(R.id.studenthome_button_awaiting_response_fake)
    private Button mAwaitingResponseButton;

    @InjectView(R.id.studenthome_button_awaiting_response_fake)
    private Button mPastProjectsButton;

    @Override
    protected int getLayoutId() {
        return R.layout.student_home_screen_fake_project;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSwitchStudentNinja = (Switch) findViewById(R.id.studenthome_switch_student_or_ninja_fake);

       if (mSwitchStudentNinja != null)
       {
            mSwitchStudentNinja.setOnCheckedChangeListener(this);
       }


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
}
