package com.calm.android.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/21/13
 * Time: 6:57 AM
 * To change this template use File | Settings | File Templates.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseApi{

    private static final String baseUrl = "https://goalyapp.appaspot.com/calm";

    protected abstract String getUrl();

    public String fetch(){


        return null;
    }




}
