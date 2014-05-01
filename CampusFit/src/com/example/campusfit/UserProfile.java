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

public class UserProfile extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile);
		setActionBar("User Profile");
		
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
		
		ageinfo.setText(GlobalVariables.age);
		phoneinfo.setText(GlobalVariables.phone);
		usernameinfo.setText(GlobalVariables.username);
		
		

	}
	
	@Override
	public void onClick(View v) {
		// 'Update Information' button has been pressed
		//*** send info back to server to update
		JSONFunctions j = new JSONFunctions();
		//EditText usernameinfo = (EditText)findViewById(R.id.username);
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
		//String username = usernameinfo.getText().toString();
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
		
		if(password.isEmpty())
			password = GlobalVariables.password;
		if(age.isEmpty())
			age = GlobalVariables.age;
		if(phone.isEmpty())
			phone = GlobalVariables.phone;
		location = location.replace(" ", "%20");
		//System.out.println("Username is : " + username );
		System.out.println("Username is : " + age );
		System.out.println("Username is : " + phone );
		System.out.println("Username is : " + location );
		System.out.println("Username is : " + gender );
		
		j.execute("http://54.245.123.104/profileUp.php?uName=" + GlobalVariables.username + "&uPass=" + password + "&uGend="+ gender + "&uAge=" + age + "&uPhone="+ phone + "&uLoc=" + location);

		
	}

}
