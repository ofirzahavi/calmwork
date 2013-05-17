package com.calm.android.api.generic;

public abstract class GetApiRequest extends ApiRequest {

	protected static final String TAG = GetApiRequest.class.toString();
	
	@Override
	public MethodType method() {
		
		return MethodType.GET;
	}

	@Override
	public String parameters() {
		return "";
	}
	
	

}
