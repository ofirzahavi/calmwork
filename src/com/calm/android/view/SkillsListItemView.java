package com.calm.android.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.activity.CalmActivity;
import com.calm.android.activity.ProjectDetailsActivity;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.model.Project;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class SkillsListItemView extends LinearLayout{

    private CalmUser mCalmUser;
    private Context mContext;
 //   private Project mProject;

    public SkillsListItemView(Context context, final String skill , CalmUser calmUser) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.skills_item, this);
        Button mTrashButton = (Button) findViewById(R.id.skill_trash_button);

        TextView skillSubjectTitleTextView = (TextView) findViewById(R.id.skill_item_title);
        skillSubjectTitleTextView.setText(skill);

    //    TextView projectTitleTextView = (TextView) findViewById(R.id.skill_item_title);
    //    projectTitleTextView.setText(project.getName());

        mCalmUser=calmUser;

        mContext = context;
        mTrashButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Runnable r = new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            mCalmUser.getSkills().remove(skill);
                                            CalmActivity.userEndpoint.updateCalmUser(mCalmUser).execute();

                                      }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                Thread t = new Thread(r);
                                t.start();


                                // continue with delete
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
            }
        });

    }

}
