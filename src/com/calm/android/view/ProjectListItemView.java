package com.calm.android.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.calm.android.R;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectListItemView extends LinearLayout {


    public ProjectListItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.project_list_item, this);
    }
}
