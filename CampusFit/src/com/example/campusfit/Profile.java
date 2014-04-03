package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Profile extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		setActionBar("Profile");
		
		View update = findViewById(R.id.update_info);
		update.setOnClickListener(this);
		
		//*** Request info from server
		
		
		//*** Update fields from server
		TextView usernameinfo = (TextView)findViewById(R.id.username);
		EditText passwordinfo = (EditText)findViewById(R.id.password);
		EditText ageinfo = (EditText)findViewById(R.id.age);
		EditText phoneinfo = (EditText)findViewById(R.id.phonenumber);
		RadioButton male = (RadioButton)findViewById(R.id.male_radio);
		RadioButton female = (RadioButton)findViewById(R.id.female_radio);
		Spinner loc = (Spinner)findViewById(R.id.location_spinner);
		Spinner goal = (Spinner)findViewById(R.id.goal_spinner);
		RadioButton traineryes = (RadioButton)findViewById(R.id.trainer_yes);
		RadioButton trainerno = (RadioButton)findViewById(R.id.trainer_no);
		
		

	}
	
	@Override
	public void onClick(View v) {
		// 'Update Information' button has been pressed
		//*** send info back to server to update
		

		
	}

}
