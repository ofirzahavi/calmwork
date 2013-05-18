package com.calm.android.api.generic;

public abstract class PutApiRequest extends PostApiRequest {

	protected static final String TAG = PutApiRequest.class.toString();
	
	@Override
	public MethodType method() {
		
		return MethodType.PUT;
	}

}
