package com.calm.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.ActionBarSherlock;
import com.calm.android.R;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 28/03/13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class CreateProjectActivity extends CalmActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected int getLayoutId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @InjectView(R.id.newProject_editText_name)
    private EditText mProjectNameText;

    @InjectView(R.id.newProject_editText_describe)
    private EditText mProjectDescriptionText;

    @InjectView(R.id.newProject_button_submit)
    private Button mSubmitButton;

    @InjectView(R.id.newProject_spinner_subject)
    private Spinner mSubjectSpinner;

    @InjectView(R.id.newProject_spinner_level)
    private Spinner mLevelSpinner;

    private String[] subjects = {"Math"};
    private String[] levels = {"Elementary school (Grades 1-5)","Middle school (Grades 6-8)", "High school (Grades 9-12)", "University / College"};


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_account_screen);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String project_name =  mProjectNameText.getText().toString();
                String project_description =  mProjectDescriptionText.getText().toString();           //TODO: set all values

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("projectName", project_name);
                editor.putString("projectDescription", project_description);

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), NewWorkActivity.class);     //TODO: change the activity that button leads to
                startActivity(intent);


            }
        });

        ArrayAdapter<String> adapterLevels = new ArrayAdapter<String>(CreateProjectActivity.this, android.R.layout.simple_spinner_item, levels);
        mLevelSpinner = (Spinner) findViewById(R.id.newProject_spinner_level);
        mLevelSpinner.setAdapter(adapterLevels);

        ArrayAdapter<String> adapterSubjects = new ArrayAdapter<String>(CreateProjectActivity.this, android.R.layout.simple_spinner_item, subjects);
        mSubjectSpinner = (Spinner) findViewById(R.id.newProject_spinner_subject);
        mLevelSpinner = (Spinner) findViewById(R.id.newProject_spinner_level);
        mLevelSpinner.setAdapter(adapterSubjects);

        mLevelSpinner.setOnItemSelectedListener(this);
        mSubjectSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
