package com.calm.android.api.login;

import com.calm.android.api.generic.GetApiRequest;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 5/17/13
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginApi extends GetApiRequest {


    private String mEmail;
    private String mPassword;

    public LoginApi(String email, String password){
        mEmail = email;
        mPassword  = password;
    }

    @Override
    public String uri() {

        String format = "/user?email=%s&password=%s";

        return String.format(format, mEmail, mPassword);
    }
}
