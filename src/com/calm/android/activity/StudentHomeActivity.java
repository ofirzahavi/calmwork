package com.calm.android.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.calm.android.R;
import roboguice.inject.InjectView;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 01/04/13
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */

public class StudentHomeActivity extends CalmActivity{

    @InjectView(R.id.studenthome_togglebutton_student_or_ninja)
    private ToggleButton toggleButton1;

    @InjectView(R.id.studenthome_button_work_in_progress)
    private Button mWorkInProgressButton;

    @InjectView(R.id.studenthome_button_create_new_project)
    private Button mNewProjectButton;

    @InjectView(R.id.studenthome_button_awaiting_response)
    private Button mAwaitingResponseButton;

    @InjectView(R.id.studenthome_button_past_projects)
    private Button mPastProjectsButton;

    @Override
    protected int getLayoutId() {
        return R.layout.student_home_screen;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnButton();

        mNewProjectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addListenerOnButton() {

        toggleButton1 = (ToggleButton) findViewById(R.id.studenthome_togglebutton_student_or_ninja);

    }

}
