package com.calm.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.calm.android.model.Work;
import com.calm.android.view.ProjectBidItemView;
import com.calm.android.view.ProjectListItemView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/1/13
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorksListAdapter extends BaseExpandableListAdapter {

    private final List<Work> worksList;
    private final Context context;

    public WorksListAdapter(Context context, List<Work> workList){
        this.context = context;
        this.worksList = workList;

    }
    @Override
    public int getGroupCount() {
        try {
            int listSize = worksList.size();
            return listSize;
        } catch (Exception e){
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        /*Work work = worksList.get(groupPosition);
        int numberOfBids = work.bids.size();
        return numberOfBids;*/
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return worksList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return new ProjectListItemView(context);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return new ProjectBidItemView(context);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
