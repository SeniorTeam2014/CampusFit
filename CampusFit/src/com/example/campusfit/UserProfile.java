package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
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
		EditText interest1 = (EditText)findViewById(R.id.interest1);
		EditText interest2 = (EditText)findViewById(R.id.interest2);
		EditText interest3 = (EditText)findViewById(R.id.interest3);
		RadioButton traineryes = (RadioButton)findViewById(R.id.trainer_yes);
		RadioButton trainerno = (RadioButton)findViewById(R.id.trainer_no);
		
		ageinfo.setText(GlobalVariables.age);
		phoneinfo.setText(GlobalVariables.phone);
		usernameinfo.setText(GlobalVariables.username);
		RetrieveInterest i = new RetrieveInterest();
		i.execute("http://54.245.123.104/viewInterests.php?uName=" + GlobalVariables.username);
		SystemClock.sleep(300);
		interest1.setText(GlobalVariables.interests.get(0));
		interest2.setText(GlobalVariables.interests.get(1));
		interest3.setText(GlobalVariables.interests.get(2));
	

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
		EditText interest1 = (EditText)findViewById(R.id.interest1);
		EditText interest2 = (EditText)findViewById(R.id.interest2);
		EditText interest3 = (EditText)findViewById(R.id.interest3);
		
		
		//*** INFORMATION TO BE SENT TO SERVER
		String password = passwordinfo.getText().toString();
		//String username = usernameinfo.getText().toString();
		String age = ageinfo.getText().toString();
		String phone = phoneinfo.getText().toString();
		String interest_1 = interest1.getText().toString();
		String interest_2 = interest2.getText().toString();
		String interest_3 = interest3.getText().toString();
		String gender;
		if (male.isChecked()) gender ="0";
		else gender = "1";
		String location = loc.getSelectedItem().toString();
		//String primary_goal = goal.getSelectedItem().toString();
		String trainer;
//		if (traineryes.isChecked()) trainer = "yes";
	//	else trainer = "no";
		
		if(password.isEmpty())
			password = GlobalVariables.password;
		if(age.isEmpty())
			age = GlobalVariables.age;
		if(phone.isEmpty())
			phone = GlobalVariables.phone;
		location = location.replace(" ", "%20");
		System.out.println("http://54.245.123.104/profileUp.php?uName=" + GlobalVariables.username + "&uPass=" + password + "&uGend="+ gender + "&uAge=" + age + "&uPhone="+ phone + "&uLoc=" + location);
		//System.out.println("Username is : " + username );
		System.out.println("Username is : " + age );
		System.out.println("Username is : " + phone );
		System.out.println("Username is : " + location );
		System.out.println("Username is : " + gender );
		
		
		j.execute("http://54.245.123.104/profileUp.php?uName=" + GlobalVariables.username + "&uPass=" + password + "&uGend="+ gender + "&uAge=" + age + "&uPhone="+ phone + "&uLoc=" + location);
		
		if(interest_1.isEmpty())
			interest_1 = " ";
		else if(interest_2.isEmpty())
			interest_2 = " ";
		else if (interest_3.isEmpty())
			interest_3 = " ";
		
		interest_1 = interest_1.replace(" ", "%20");
		interest_2 = interest_2.replace(" ", "%20");
		interest_3 = interest_3.replace(" ", "%20");
		
		JSONFunctions2 j2 = new JSONFunctions2();
		System.out.println("http://54.245.123.104/interest.php?uName=" + GlobalVariables.username + "&uInt1=" + interest_1 + "&uInt2=" + interest_2 + "&uInt3=" + interest_3);
		j2.execute("http://54.245.123.104/interest.php?uName=" + GlobalVariables.username + "&uInt1=" + interest_1 + "&uInt2=" + interest_2 + "&uInt3=" + interest_3);
		
	}

}
