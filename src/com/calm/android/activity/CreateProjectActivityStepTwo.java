package com.calm.android.activity;

import android.os.Bundle;
import android.widget.Spinner;
import com.calm.android.R;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 30/03/13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class CreateProjectActivityStepTwo extends CalmActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.new_project_screen_2;
    }

    @InjectView(R.id.newproject_spinner_date_day)
    private Spinner mDaySpinner;

    @InjectView(R.id.newproject_spinner_date_month)
    private Spinner mMonthSpinner;

    @InjectView(R.id.newproject_spinner_date_year)
    private Spinner mYearSpinner;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection() {
        mDaySpinner = (Spinner) findViewById(R.id.newproject_spinner_date_day);
        mDaySpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mMonthSpinner = (Spinner) findViewById(R.id.newproject_spinner_date_month);
        mMonthSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mYearSpinner = (Spinner) findViewById(R.id.newproject_spinner_date_year);
        mYearSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }
}
