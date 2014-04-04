package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Cooldown extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	public void onCreate(Bundle savedInstanceState) {
		setActionBar("Sample Workouts - Cool Down");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cooldown);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
