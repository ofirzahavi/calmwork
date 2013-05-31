package com.calm.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.calm.android.R;
import com.calm.android.adapter.ProjectsListAdapter;
import com.calm.android.model.Project;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 24/05/13
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */
public class TeacherProfile extends CalmActivity {

    @InjectView(R.id.ratetext)
    private TextView mRatingText;

    @Override
    protected int getLayoutId() {
        return R.layout.teacher_profile;
    }

    RatingBar rb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rb=(RatingBar)findViewById(R.id.ratingbar1);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                // TODO Auto-generated method stub
               // Toast.makeText(getApplicationContext(), Float.toString(rating), Toast.LENGTH_LONG).show();
                mRatingText.setText("Rating: "+String.valueOf(rating));

            }

        });
    }
}

