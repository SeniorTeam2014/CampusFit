package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TrainerMainMenu extends Activity implements OnClickListener{
	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setActionBar("Trainer Main Menu");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trainermenu);
		
		// setup 'profile' button
		View profile = findViewById(R.id.profile);
		profile.setOnClickListener(this);
		
		// setup 'templates' button
		View templates = findViewById(R.id.templates);
		templates.setOnClickListener(this);
		
		// setup 'workouts' button
		View workouts = findViewById(R.id.workouts);
		workouts.setOnClickListener(this);
		
		// setup 'creatgroup' button
		View creategroup = findViewById(R.id.creategroup);
		creategroup.setOnClickListener(this);
		
		// setup 'find group' button
		View findgroup = findViewById(R.id.findgroup);
		findgroup.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.profile:
			// profile button pressed
			try {
				Intent i1 = new Intent(this, TrainerProfile.class);
				startActivity(i1);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.templates:
			// templates button pressed
			try {
				Intent i2 = new Intent(this, Templates.class);
				startActivity(i2);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.workouts:
			// templates button pressed
			try {
				Intent i3 = new Intent(this, Workouts.class);
				startActivity(i3);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case R.id.creategroup:
			// creategroup button pressed
			try {
				Intent i7 = new Intent(this, CreateGroup.class);
				startActivity(i7);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case R.id.findgroup:
			// findgroup button pressed
			try {
				Intent i8 = new Intent(this, FindGroup.class);
				startActivity(i8);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
		
		}
		
	}
}
