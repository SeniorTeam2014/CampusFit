package com.example.campusfit;

import java.util.concurrent.ExecutionException;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setActionBar("Campus Fit");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// setup 'signup' button that goes to the signup xml
		View signup = findViewById(R.id.signup);
		signup.setOnClickListener(this);

		// setup 'enter' button that goes to the main menu xml
		View enter = findViewById(R.id.enter);
		enter.setOnClickListener(this);
		
		View edittext1 = findViewById(R.id.editText1);
		edittext1.requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
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
			{
				try {
					j.execute("http://54.245.123.104/profile.php?uName=" + username).get();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	}

			String checkuser = j.getUsername();
			String checkpass = j.getPassword();
			System.out.println(checkuser+", "+ checkpass);

			if(checkuser.equals(username) && checkpass.equals(password))
			{
				//*** check if user or trainer
				
				try {
					//*** if user, Intent i2 = new Intent(this, UserMainMenu.class);
					//*** if trainer, Intent i2 = new Intent(this, TrainerMainMenu.class);
					Intent i2 = new Intent(this, TrainerMainMenu.class);
					startActivity(i2);
				} catch (ActivityNotFoundException e) {
					e.printStackTrace();
				}
			}
			else
			{
				// wrong username or password. show toast
				Context context = getApplicationContext();
				CharSequence text = "Incorrect username or password";
				int duration = Toast.LENGTH_SHORT;

				Toast t = Toast.makeText(context, text, duration);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
				Log.v("wrong login", "You entered wrong username/password combo");
				break;
			}
		} 

	}

}

