package com.calm.android.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/21/13
 * Time: 3:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class Work {
    public static final int NEW = 0;
    public static final int BIDS = 1;
    public static final int IN_PROGRESS = 2;
    public static final int FINISHED = 3;
    public static final int COMPLETED = 4;

    public int status = 0;
    public String title;
    public String description;
    public int price;
    public String owner;
    public String teacher;
    public ArrayList<User> bids = new ArrayList<User>();

    public Work(){

    }

}
