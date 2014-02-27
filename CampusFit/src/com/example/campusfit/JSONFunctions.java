package com.example.campusfit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.os.AsyncTask;
import android.util.Log;

public class JSONFunctions extends AsyncTask<String,Void,String> {
	InputStream is = null;
	String result = "";
	String username = "test";
	String password = "test";
	@Override
	protected String doInBackground(String... params) {
		try {

	        HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(params[0]);
	        HttpResponse response = httpclient.execute(httppost);
	        HttpEntity entity = response.getEntity();
	     

	        is = entity.getContent();
	        Log.v("mysql", "Success");
	    } catch (Exception e) {
	        Log.v("log_tag", "Error in http connection " + e.toString());
	    }
		
		try{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	        }
	        is.close();
	 
	        result=sb.toString();
	        Log.v("result", result);
	}catch(Exception e){
	        Log.e("log_tag", "Error converting result "+e.toString());
	}
		
		try{
	        
			JSONObject jObject = null;
			jObject = new JSONObject(result);
			username = (String)jObject.get("uName");
			password = (String)jObject.get("uPass");
			//Log.v("json user", getUsername());
	}catch(JSONException e){
	        Log.e("log_tag", "Error parsing data "+e.toString());
	        username = null;
	        password = null;
		}
	
		return null;
	}
	
	protected void onPostExecute(String result) {
		//do nothing
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
}
