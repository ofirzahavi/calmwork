package com.calm.android.api.login;

import com.calm.android.api.generic.PostApiRequest;
import com.calm.android.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 5/17/13
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SignUp extends PostApiRequest {


    //private User mUser;
    private String email;
    private String userName;
    private String password;

    public SignUp(User user){
        this.email = user.email;
        this.userName = user.name;
        this.password = user.password;
    }

    @Override
    public String uri() {

        return "/user";//?email=%s&password=%s&userName=%s";

        //return String.format(format, mEmail, mPassword, mUserName);
    }
}
