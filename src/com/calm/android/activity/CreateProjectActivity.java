package com.calm.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.ActionBarSherlock;
import com.calm.android.R;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 28/03/13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class CreateProjectActivity extends CalmActivity {

    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    TextView lblDateAndTime;
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.new_project_screen;
    }

    @InjectView(R.id.newproject_edittext_name)
    private EditText mProjectNameText;

    @InjectView(R.id.newproject_button_next)
    private Button mNextButton;

    @InjectView(R.id.newproject_spinner_subject)
    private Spinner mSubjectSpinner;

    @InjectView(R.id.newproject_spinner_level)
    private Spinner mLevelSpinner;

    @InjectView(R.id.newproject_spinner_language)
    private Spinner mLanguageSpinner;

    @InjectView(R.id.newproject_button_due_date)
    private Button mDueDateButton;

    @InjectView(R.id.newproject_button_budget)
    private Button mBudgetButton;

    @InjectView(R.id.newproject_button_add_files)
    private Button mAddFilesButton;

    //TODO: add dateChooser member

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();
        mDueDateButton = (Button) findViewById(R.id.newproject_duedate);
        mDueDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(CreateProjectActivity.this, d, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });
        updateLabel();



        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String project_name =  mProjectNameText.getText().toString();
                //String project_description =  mProjectDescriptionText.getText().toString();   //TODO: set all values

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("projectName", project_name);
                //editor.putString("projectDescription", project_description);

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), CreateProjectActivityStepTwo.class);
                startActivity(intent);

            }
        });
    }

    public void addListenerOnSpinnerItemSelection() {
        mLevelSpinner = (Spinner) findViewById(R.id.newproject_spinner_level);
        mLevelSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mSubjectSpinner = (Spinner) findViewById(R.id.newproject_spinner_level);
        mSubjectSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mLanguageSpinner = (Spinner) findViewById(R.id.newproject_spinner_language);
        mLanguageSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
