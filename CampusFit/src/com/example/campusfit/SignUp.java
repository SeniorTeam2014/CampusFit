package com.example.campusfit;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

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
		EditText usernameinfo = (EditText)findViewById(R.id.editText1);
		EditText passwordinfo = (EditText)findViewById(R.id.editText2);
		EditText confirmpass = (EditText)findViewById(R.id.editText3);
		
		String password = passwordinfo.getText().toString();
		String username = usernameinfo.getText().toString();
		String passconfirm = confirmpass.getText().toString();
		
		Log.v("checl", username);
		Log.v("checl", password);
		Log.v("checl", passconfirm);
		
		JSONFunctions j = new JSONFunctions();
		
		if(password.equals(passconfirm))
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
		
		
		
		
	}

}
