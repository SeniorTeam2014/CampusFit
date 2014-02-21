package com.example.campusfit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SignUp extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		// setup 'create account' button
		View create = findViewById(R.id.create_account);
		create.setOnClickListener(this);
	}
		
	@Override
	public void onClick(View v) {
		// 'create account' button has been pressed. add functionality
		
		
	}

}
