package com.calm.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import com.calm.android.view.ProjectListItemView;
import com.google.api.services.projectendpoint.model.Project;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 10/05/13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class ProjectsListAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Project> mProjects;

    public ProjectsListAdapter(Context context, List<Project> projects){
        mContext = context;
        mProjects = projects;
    }

    @Override
    public int getCount() {
        if (mProjects==null){
            return 0;
        }
        return mProjects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int position) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Project project = mProjects.get(position);
        ProjectListItemView projectView = new ProjectListItemView(mContext, project);

        return projectView;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
