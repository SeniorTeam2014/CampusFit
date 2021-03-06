package com.example.campusfit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONFunctions extends AsyncTask<String,Void,String> {
	InputStream is = null;
	String result = "";
	String username = "test";
	String password = "test";
	String age = "test";
	String location = "test";
	String gender = "test";
	String phone = "test";
	String train = "test";
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
			train = (String)jObject.getString("Trainer");
			GlobalVariables.username = (String)jObject.get("uName");
			GlobalVariables.password = (String)jObject.get("uPass");
			GlobalVariables.phone = (String)jObject.get("Phone");
			GlobalVariables.gender = (String)jObject.get("Gender");
			GlobalVariables.age = (String)jObject.get("Age");
			GlobalVariables.phone = (String)jObject.get("Phone");
			GlobalVariables.location = (String)jObject.get("Location"); 
			//Log.v("json user", getUsername());
	}catch(JSONException e){
	        Log.e("log_tag", "Error parsing data "+e.toString());
	        GlobalVariables.username = "test1";
	        password = "test1";
	        GlobalVariables.age = "test1";
	        GlobalVariables.username = "test1";
	        GlobalVariables.gender = "test1";
	        GlobalVariables.location = "test1";
	        GlobalVariables.phone = "test1";
		}
	
		return null;
	}
	
	@Override
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
	public String getTrain()
	{
		return train;
	}
}
