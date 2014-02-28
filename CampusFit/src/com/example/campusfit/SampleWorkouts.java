package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SampleWorkouts extends Activity implements OnClickListener {
	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}

	public void onCreate(Bundle savedInstanceState) {
		setActionBar("Sample Workouts");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sampleworkout);

		// setup cooldown button
		View cooldown = findViewById(R.id.cooldown);
		cooldown.setOnClickListener(this);

		// setup warmup button
		View warmup = findViewById(R.id.warmup);
		warmup.setOnClickListener(this);

		// setup sample workouts button
		View samplework = findViewById(R.id.samplework);
		samplework.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.cooldown:
			// profile button pressed
			try {
				Intent i1 = new Intent(this, Cooldown.class);
				startActivity(i1);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case R.id.warmup:
			// templates button pressed
			try {
				Intent i2 = new Intent(this, WarmUp.class);
				startActivity(i2);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case R.id.samplework:
			// workouts button pressed
			try {
				Intent i3 = new Intent(this, SampleWork.class);
				startActivity(i3);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}

	}
}
