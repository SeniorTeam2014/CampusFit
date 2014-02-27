package com.example.campusfit;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// setup 'signup' button that goes to the signup xml
		View signup = findViewById(R.id.signup);
		signup.setOnClickListener(this);
		
		// setup 'enter' button that goes to the main menu xml
		View enter = findViewById(R.id.enter);
		enter.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// check which button is pressed
		switch (v.getId()) {

		case R.id.signup:
			// sign up button pressed, goto sign up screen
			try {
				Intent i1 = new Intent(this, SignUp.class);
				startActivity(i1);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;

		
		case R.id.enter:
			// enter button is pressed, goto main menu
			EditText usernameinfo = (EditText)findViewById(R.id.editText1);
			EditText passwordinfo = (EditText)findViewById(R.id.editText2);
			
			String password = passwordinfo.getText().toString();
			String username = usernameinfo.getText().toString();
			
			
			JSONFunctions j = new JSONFunctions();
			
			if(password != null && username != null)
				j.execute("http://54.245.123.104/test.php?uName=" + username);
			
			String checkuser = j.getUsername();
			String checkpass = j.getPassword();
			Log.v("username", checkuser);
			Log.v("username", checkpass);
			//if(checkuser.equals(username) && checkpass.equals(password))
			//{
				try {
					Intent i2 = new Intent(this, MainMenu.class);
					startActivity(i2);
				} catch (ActivityNotFoundException e) {
					e.printStackTrace();
				}
			//}
			//else
				//Log.v("wrong login", "You entered wrong username/password");
			break;
			
		} 
		
	}


}
