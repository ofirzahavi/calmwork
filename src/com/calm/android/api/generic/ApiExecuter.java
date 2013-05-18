package com.calm.android.api.generic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.calm.android.api.generic.ApiRequest.MethodType;

public class ApiExecuter extends AsyncTask<ApiRequest, Integer, String>{
	
	public static final String HTTP_STR = "http://";
	public static final String BASE_URL = "biuninja2013.appspot.com";
	//private static final String JSON_CONTENT_TYPE = "application/json"; //application/x-www-form-urlencoded
    private static final String JSON_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private Context mContext;
	
	private static DefaultHttpClient sHttpClient = new DefaultHttpClient();
	private ProgressDialog mProgressDialog = null;
	private ApiHandler mHandler;
	private boolean mExceptionHappaned = false;
	private CharSequence mErrorMessage = null;
	
	public ApiExecuter(Context context, ApiHandler handler){
		mContext = context;
		mHandler = handler;
		
	}
	
	public ApiExecuter(Context context){
		this(context, new ApiHandler() {
			@Override
			public void handle(String response) {
			}
		});
	}

	public ApiExecuter(){
		this(null);
	}
		
	public String exec(ApiRequest api) throws ApiException{
		

			//Utils.Log(LogLevel.Debug,TAG, "executing " + getURL(api));
        System.out.println("executing " + getURL(api));
			HttpUriRequest httpRequest = putParameters(api);
			//Utils.Log(LogLevel.Debug,TAG, "parameters: " + api.parameters());
        System.out.println("parameters: " + api.parameters());

			HttpResponse response;
			try {
				response = sHttpClient.execute(httpRequest);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApiException("error");
			}
			
			return api.handleResponse(response);
			//return true;
	}

	protected HttpUriRequest putParameters(ApiRequest api) {
		
		HttpUriRequest httpRequest = null;
		ApiRequest.MethodType requestMethod = api.method();
		
		switch (requestMethod){
			case POST:
			case PUT:
				httpRequest = initHttpPostWithJSON(requestMethod, api);
				 break;
			case GET:
				try {
					httpRequest = initHttpGet(api);
				} catch (JSONException e) {
					// 
					e.printStackTrace();
					httpRequest = null;
				}
				break;
			case DELETE:
				httpRequest = initHttpDelete(api);
				break;
		}
		
		return httpRequest;
		
	}
	
	private HttpUriRequest initHttpGet(ApiRequest api) throws JSONException {
		String parameters = api.parameters();
		HttpGet httpGet = new HttpGet(getURL(api) + parameters);
		httpGet.setHeader("Accept", JSON_CONTENT_TYPE);
		httpGet.setHeader("Content-type", JSON_CONTENT_TYPE);
		return httpGet;
	}
	
	private HttpUriRequest initHttpDelete(ApiRequest api){
		HttpDelete httpDelete = new HttpDelete(getURL(api));
		httpDelete.setHeader("Accept", JSON_CONTENT_TYPE);
		httpDelete.setHeader("Content-type", JSON_CONTENT_TYPE);
		return httpDelete;
	}


	private HttpUriRequest initHttpPostWithJSON(MethodType requestMethod, ApiRequest api) {
		HttpEntityEnclosingRequestBase httpRequest;
		if (requestMethod.equals(MethodType.POST)){
			httpRequest = new HttpPost(getURL(api));
		} else{
			httpRequest = new HttpPut(getURL(api));
		}
		String parametersJsonString = api.parameters();
		
			StringEntity se;
		try {
			
				if (parametersJsonString != null) {
				// create entity out of JSON data
				se = new StringEntity(parametersJsonString, "UTF-8");
				httpRequest.setEntity(se);
			}
			// set headers
			httpRequest.setHeader("Accept", JSON_CONTENT_TYPE);
			httpRequest.setHeader("Content-type", JSON_CONTENT_TYPE);

			// we're good to go
			return httpRequest;
		} catch (UnsupportedEncodingException e) {
				//Utils.Log(LogLevel.Error, TAG, e.toString(),e);
		}
		
		return null;
	}
	
	
	
	protected String getURL(ApiRequest api) {
		StringBuilder stringBuilder = new StringBuilder();
		
		String url = stringBuilder.append(HTTP_STR)
				.append(BASE_URL)
				.append(api.uri())
				.toString();
		return url;
	}



	 @Override
     protected void onPreExecute()
     {
        if (mContext != null && mProgressDialog==null){
        	mProgressDialog = ProgressDialog.show(mContext, "",
	                 "Loading...");
        }
     }
	 
	 @Override
	protected String doInBackground(ApiRequest... params) {
		String response;
		try {
			response = exec(params[0]);
			return response;
		} catch (Exception e) {
			mExceptionHappaned = true;
			mErrorMessage = e.getMessage();
			if (mErrorMessage == null){
		//		mErrorMessage = mContext.getResources().getString(R.string.Server_Error);
                mErrorMessage = "serverProblem";
			}
			e.printStackTrace();
			return null;
		} 
		
	 }
	 
	 @Override
	 protected void onPostExecute(String result) {
		 if (mExceptionHappaned){
			 mExceptionHappaned=false;
			new AlertDialog.Builder(mContext).setTitle("error").setMessage(mErrorMessage).setNeutralButton("OK", null).show();
		 } else{
			 mHandler.handle(result);
		 }
		if (mProgressDialog != null){
			//TODO: move progressDialog to activity and cancel before destroying activity
			//mProgressDialog.cancel();
			mProgressDialog.dismiss();
			mProgressDialog = null;
   	 	}     
		}
	}
