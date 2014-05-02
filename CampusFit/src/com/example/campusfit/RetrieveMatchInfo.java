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

public class RetrieveMatchInfo extends AsyncTask<String,Void,String> {
	InputStream is = null;
	String result = "";
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
			GlobalVariables.mname = (String)jObject.get("uName");
			GlobalVariables.mage = (String)jObject.get("Age");
			GlobalVariables.mphone = (String)jObject.get("Phone");
			//Log.v("json user", getUsername());
	}catch(JSONException e){
	        Log.e("log_tag", "Error parsing data "+e.toString());
	        GlobalVariables.mname = "TEST";
	        GlobalVariables.mage = "TEST";
	        GlobalVariables.mphone = "TEST";
		}
	
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		//do nothing
	}
	
}
