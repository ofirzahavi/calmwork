package com.calm.android.api.generic;

import com.google.gson.Gson;

public abstract class PostApiRequest extends ApiRequest {

	protected static final String TAG = PostApiRequest.class.toString();
	
	@Override
	public MethodType method() {
		
		return MethodType.POST;
	}

	@Override
	public String parameters() {
		Gson gson = new Gson();
		String parameters = gson.toJson(this);
		return parameters;
	}
	
}
