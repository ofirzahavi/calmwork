package com.calm.android.api.generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public abstract class ApiRequest {

	public enum MethodType{
		GET, POST, PUT, DELETE;
	}

	private static final String ERROR = "ErrorCode";
	private static final String ERROR_MSG = "Message";
	
	public abstract MethodType method();
	
	public abstract String uri();
	
	public abstract String parameters();
	
	public String handleResponse(HttpResponse response) throws ApiException{
		String responseData = getResponseData(response);
	//	Utils.Log(LogLevel.Debug, "API", "response:" + responseData);
		if (isError(responseData)){
			String message = errorMessage(responseData);
			throw new ApiException(message);
		}
		return responseData;
	}
	
	private String errorMessage(String response) {
		JSONObject json;
		try {
			json = new JSONObject(response);
			String error = json.optString(ERROR_MSG, null);
			return error;
		} catch (JSONException e) {
			return null;
		}
	}

	protected boolean isError(String response){
		JSONObject json;
		try {
			json = new JSONObject(response);
			String error = json.optString(ERROR, null);
			if (error == null){
				return false;
			}
		} catch (JSONException e) {
			try {
				new JSONArray(response);
			} catch (JSONException e1) {
				return true;
			}
			return false;
		}
		return true;
	}

    public static String getResponseData(HttpResponse response) {
        BufferedReader bufferedReader = null;
        try {
            InputStream is = response.getEntity().getContent();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            bufferedReader = new BufferedReader(isr);
            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            String data = stringBuffer.toString();
            return data;
        } catch (Exception e) {
            //Utils.Log(LogLevel.Error,TAG, e.toString(),e);
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
              //      Utils.Log(LogLevel.Error,TAG, e.toString(),e);
                }
            }
        }
    }
}
