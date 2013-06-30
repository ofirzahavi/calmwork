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
import android.widget.Toast;
import com.calm.android.R;
import com.calm.android.activity.CalmActivity;
import com.calm.android.activity.ProjectDetailsActivity;
import com.google.api.services.projectendpoint.model.Project;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectTeacherListItemView extends LinearLayout{

    private Project mProject;
    private Context mContext;
 //   private Project mProject;

    public ProjectTeacherListItemView(Context context, final Project project) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.project_teacher_item, this);
        Button mAcceptButton = (Button) findViewById(R.id.project_teacher_list_accept_button);

        TextView projectTitleTextView = (TextView) findViewById(R.id.project_item_teacher_title);
        projectTitleTextView.setText(project.getSubject() + " Assignment");

        mProject=project;

        TextView projectDateTextView = (TextView) findViewById(R.id.project_item_teacher_date);
     //   projectTitleTextView.setText(project.dueDate.toString());

        String[] separated = project.getDueDate().toString().split("T");
        String[] separatedFinal = separated[0].split("-");
        String correctDate =  separatedFinal[2] + "-" + separatedFinal[1] + "-" + separatedFinal[0]   ;
        projectDateTextView.setText(correctDate);



        setRowOnClickListener(context);
        mContext = context;
        mAcceptButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, ("Project Accepted"),
                        Toast.LENGTH_SHORT).show();
                /*
                new AlertDialog.Builder(mContext)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {

                                Runnable r = new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            CalmActivity.projectEndpoint.removeProject(project.getProjectId()).execute();

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
                        .show();  */
            }
        });

    }


    private void setRowOnClickListener(final Context context) {

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // final Dialog dialog = new Dialog(context);
             //   dialog.setContentView(R.layout.project_screen);
                Intent intent = new Intent(mContext, ProjectDetailsActivity.class);
                intent.putExtra("projectId" , mProject.getProjectId()) ;

                String pid =   mProject.getProjectId();
                System.out.println("********** project id: " + pid );
                mContext.startActivity(intent);
              //  dialog.show();
            }


        });
    }
}
