package com.example.campusfit;

import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SignUp extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {	
		setActionBar("Create an Account");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		// setup 'create account' button
		//View create = findViewById(R.id.create_account);
		View create = findViewById(R.id.create_account);
		create.setOnClickListener(this);
	}
		
	@Override
	public void onClick(View v) {
		// 'create account' button has been pressed. add functionality
		EditText usernameinfo = (EditText)findViewById(R.id.username);
		EditText passwordinfo = (EditText)findViewById(R.id.password);
		//EditText confirmpass = (EditText)findViewById(R.id.editText6);
		
		String password = passwordinfo.getText().toString();
		String username = usernameinfo.getText().toString();
		//String passconfirm = confirmpass.getText().toString();
		
		Log.v("checl", username);
		Log.v("checl", password);
		//Log.v("checl", passconfirm);
		
		JSONFunctions j = new JSONFunctions();
		
		//if(password.equals(passconfirm))
		if(!password.isEmpty())
		{
			try {
				j.execute("http://54.245.123.104/register.php?uName=" + username + "&uPass=" + password).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			Intent i1 = new Intent(this, MainActivity.class);
			startActivity(i1);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
