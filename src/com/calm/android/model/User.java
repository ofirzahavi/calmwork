package com.calm.android.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/21/13
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    @SerializedName("userName") public String name;
    @SerializedName("email") public String email;
    @SerializedName("password") public String password;

    //public ArrayList<Work> workList = new ArrayList<Work>();

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
