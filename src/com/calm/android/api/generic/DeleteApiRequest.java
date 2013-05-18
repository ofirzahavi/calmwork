package com.calm.android.api.generic;

public abstract class DeleteApiRequest extends GetApiRequest {

	protected static final String TAG = DeleteApiRequest.class.toString();
	
	@Override
	public MethodType method() {
		
		return MethodType.DELETE;
	}

}
