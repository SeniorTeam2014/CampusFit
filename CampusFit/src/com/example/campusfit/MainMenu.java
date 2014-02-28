package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainMenu extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	public void onCreate(Bundle savedInstanceState) {
		setActionBar("Main Menu");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		// setup 'profile' button
		View profile = findViewById(R.id.profile);
		profile.setOnClickListener(this);
		
		// setup 'templates' button
		View templates = findViewById(R.id.templates);
		templates.setOnClickListener(this);
		
		// setup 'workouts' button
		View workouts = findViewById(R.id.workouts);
		workouts.setOnClickListener(this);
		
		// setup 'matchmaker' button
		View matchmaker = findViewById(R.id.matchmaker);
		matchmaker.setOnClickListener(this);
		
		View findtrainer = findViewById(R.id.findatrainer);
		findtrainer.setOnClickListener(this);
		
		// setup 'sampleworkouts' button
		View sampleworkouts = findViewById(R.id.sampleworkouts);
		sampleworkouts.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.profile:
			// profile button pressed
			try {
				Intent i1 = new Intent(this, Profile.class);
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
			// workouts button pressed
			try {
				Intent i3 = new Intent(this, Workouts.class);
				startActivity(i3);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.matchmaker:
			// matchmaker button pressed
			try {
				Intent i4 = new Intent(this, MatchmakerList.class);
				startActivity(i4);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.findatrainer:
			// matchmaker button pressed
			try {
				Intent i5 = new Intent(this, FindTrainer.class);
				startActivity(i5);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.sampleworkouts:
			// sampleworkouts button pressed
			try {
				Intent i6 = new Intent(this, SampleWorkouts.class);
				startActivity(i6);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
		
		
		
		}
		
	}

}
