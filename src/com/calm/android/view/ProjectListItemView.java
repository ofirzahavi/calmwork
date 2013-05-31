package com.calm.android.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.activity.SignupActivity;
import com.calm.android.model.Project;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectListItemView extends LinearLayout {


    private Context mContext;

    public ProjectListItemView(Context context, Project project) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.project_item, this);
        Button mTrashButton = (Button) findViewById(R.id.list_trash_button);

        TextView projectTitleTextView = (TextView) findViewById(R.id.project_item_title);
        projectTitleTextView.setText(project.title);

        TextView projectDateTextView = (TextView) findViewById(R.id.project_item_date);
     //   projectTitleTextView.setText(project.dueDate.toString());
        projectDateTextView.setText("17/05/2013");

        setRowOnClickListener(context);
        mContext = context;
        mTrashButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
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


    private void setRowOnClickListener(final Context context) {

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.project_screen);

                dialog.show();
            }

        });
    }
}
