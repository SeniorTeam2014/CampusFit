package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Stretches extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	public void onCreate(Bundle savedInstanceState) {	
		setActionBar("Sample Workouts - Streches");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stretches);
		//View more = findViewById(R.id.more);
		//more.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		/*
		if(v.getId() == R.id.more){
			try {
				Intent i1 = new Intent(this, Stretches2.class);
				startActivity(i1);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
		}
		*/
	}

}
