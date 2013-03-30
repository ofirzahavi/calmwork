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

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 28/03/13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class CreateProjectActivity extends CalmActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.new_project_screen;
    }

    @InjectView(R.id.newproject_edittext_name)
    private EditText mProjectNameText;

    @InjectView(R.id.newproject_edittext_describe)
    private EditText mProjectDescriptionText;

    @InjectView(R.id.newproject_button_next)
    private Button mNextButton;

    @InjectView(R.id.newproject_spinner_subject)
    private Spinner mSubjectSpinner;

    @InjectView(R.id.newproject_spinner_level)
    private Spinner mLevelSpinner;

    @InjectView(R.id.newproject_spinner_language)
    private Spinner mLanguageSpinner;

    //TODO: add dateChooser member

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();



        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String project_name =  mProjectNameText.getText().toString();
                String project_description =  mProjectDescriptionText.getText().toString();   //TODO: set all values

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("projectName", project_name);
                editor.putString("projectDescription", project_description);

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
