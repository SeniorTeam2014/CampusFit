package com.example.campusfit;

import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class SignUp extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {	
		setActionBar("Create an Account");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		// setup 'create account' button
		//View create = findViewById(R.id.create_account);
		View create = findViewById(R.id.create_account);
		create.setOnClickListener(this);
	}
		
	@Override
	public void onClick(View v) {
		// 'create account' button has been pressed. add functionality
		EditText usernameinfo = (EditText)findViewById(R.id.username);
		EditText passwordinfo = (EditText)findViewById(R.id.password);
		EditText ageinfo = (EditText)findViewById(R.id.age);
		EditText phoneinfo = (EditText)findViewById(R.id.phonenumber);
		RadioButton male = (RadioButton)findViewById(R.id.male_radio);
		//RadioButton female = (RadioButton)findViewById(R.id.female_radio);
		Spinner loc = (Spinner)findViewById(R.id.location_spinner);
		Spinner goal = (Spinner)findViewById(R.id.goal_spinner);
		RadioButton traineryes = (RadioButton)findViewById(R.id.trainer_yes);
		//RadioButton trainerno = (RadioButton)findViewById(R.id.trainer_no);
		
		
		
		//*** INFORMATION TO BE SENT TO SERVER
		String password = passwordinfo.getText().toString();
		String username = usernameinfo.getText().toString();
		String age = ageinfo.getText().toString();
		String phone = phoneinfo.getText().toString();
	
		String gender;
		if (male.isChecked()) gender ="0";
		else gender = "1";
		String location = loc.getSelectedItem().toString();
		String primary_goal = goal.getSelectedItem().toString();
		String trainer;
		if (traineryes.isChecked()) trainer = "yes";
		else trainer = "no";
		
		//***
		/*
		Log.v("checl", username);
		Log.v("checl", password);
		Log.v("checl", age);
		Log.v("checl", phone);
		Log.v("checl", gender);
		Log.v("checl", location);
		Log.v("checl", primary_goal);
		Log.v("checl", trainer);
		*/
		
		
		JSONFunctions j = new JSONFunctions();
		location = "happy land";
		location = location.replace(" ", "%20");
		//if(password.equals(passconfirm))
		if(!password.isEmpty())
		{
		j.execute("http://54.245.123.104/signup.php?uName=" + username + "&uPass=" + password + "&uGend="+ gender + "&uAge=" + age + "&uPhone="+ phone + "&uLoc=" + location + "&uTrain=" + trainer + "&uAct=running");
//		j.execute("http://54.245.123.104/register.php?uName=" + username + "&uPass=" + password).get();
		}
		
		
		
		
		
		
		try {
			Intent i1 = new Intent(this, MainActivity.class);
			startActivity(i1);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
