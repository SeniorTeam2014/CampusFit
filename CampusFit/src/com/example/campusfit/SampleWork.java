package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SampleWork extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	
	public void onCreate(Bundle savedInstanceState) {
		setActionBar("A Sample Workout");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startingworkout);
		
/*		
		View warmup = findViewById(R.id.stretches);
		warmup.setOnClickListener(this);
		*/
	}
	
	@Override
	public void onClick(View v) {
		/*
		if(v.getId() == R.id.stretches){
			try {
				Intent i1 = new Intent(this, Stretches.class);
				startActivity(i1);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
		}
		*/
	}

}
