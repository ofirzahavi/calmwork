package com.calm.android.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.model.Project;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectListItemView extends LinearLayout {


    public ProjectListItemView(Context context, Project project) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.project_item, this);

        TextView projectTitleTextView = (TextView) findViewById(R.id.project_item_title);
        projectTitleTextView.setText(project.title);

        TextView projectDateTextView = (TextView) findViewById(R.id.project_item_date);
     //   projectTitleTextView.setText(project.dueDate.toString());
        projectDateTextView.setText("17/05/2013");

        setOnClickListener(context);

    }

    private void setOnClickListener(final Context context) {

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
