package com.calm.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.calm.android.view.ProjectListItemView;
import com.calm.android.view.SkillsListItemView;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.model.Project;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 10/05/13
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class SkillsListAdapter extends BaseAdapter {

    private final Context mContext;
    private final CalmUser mCalmUser;

    List<String> mSkillsList;



    public SkillsListAdapter(Context context, CalmUser calmUser){
        mContext = context;
        mCalmUser = calmUser;

        if (mCalmUser!=null){
            mSkillsList = mCalmUser.getSkills();
        }

    }

    @Override
    public int getCount() {
        if (mSkillsList!=null)
            return mSkillsList.size();
        else
            return 0;
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

        String skill = mSkillsList.get(position);

       // CalmUser user = mCalmUser.get(position);
        SkillsListItemView skillView = new SkillsListItemView(mContext, skill, mCalmUser);

        return skillView;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
