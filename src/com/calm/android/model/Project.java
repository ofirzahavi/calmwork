package com.calm.android.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 10/05/13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public class Project {
    public Project(String title){
        this.title = title;
    }
    public String projectId;
    public String title;
    public Date dueDate;
    public int status;
}
