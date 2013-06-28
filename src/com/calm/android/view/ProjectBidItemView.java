package com.calm.android.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.calm.android.R;
import com.calm.android.activity.CalmActivity;
import com.google.api.services.projectendpoint.model.Project;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 4/3/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectBidItemView extends LinearLayout {


    public ProjectBidItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.line_accepts, this);
    }

    /**
     * Created with IntelliJ IDEA.
     * User: ZAHAVI
     * Date: 26/06/13
     * Time: 23:01
     * To change this template use File | Settings | File Templates.
     */

}
