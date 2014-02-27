package com.example.campusfit;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

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
			try {
				Intent i2 = new Intent(this, MainMenu.class);
				startActivity(i2);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
		} 
		
	}


}
