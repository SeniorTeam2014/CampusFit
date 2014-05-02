package com.example.campusfit;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateGroup extends Activity implements OnClickListener {

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creategroup);
		setActionBar("Create Group");
		
		View update = findViewById(R.id.update_info);
		update.setOnClickListener(this);
		
		//*** Request info from server
		
		
		//*** Update fields from server
		EditText groupname = (EditText)findViewById(R.id.groupname);
		EditText description = (EditText)findViewById(R.id.groupdescription);
		EditText owner = (EditText)findViewById(R.id.groupowner);
		EditText phone = (EditText)findViewById(R.id.ownerphone);
		Spinner loc = (Spinner)findViewById(R.id.location_spinner);
		Spinner goal = (Spinner)findViewById(R.id.goal_spinner);
		

	}
	
	@Override
	public void onClick(View v) {
		// 'Update Information' button has been pressed
		
		//*** send info back to server to update
		JSONFunctions j = new JSONFunctions();
		EditText groupname = (EditText)findViewById(R.id.groupname);
		EditText groupdescription = (EditText)findViewById(R.id.groupdescription);
		EditText owner = (EditText)findViewById(R.id.groupowner);
		EditText phone = (EditText)findViewById(R.id.ownerphone);
		Spinner loc = (Spinner)findViewById(R.id.location_spinner);
		Spinner goal = (Spinner)findViewById(R.id.goal_spinner);

		//*** INFORMATION TO BE SENT TO SERVER
		String grpname = groupname.getText().toString();
		String grpdescription = groupdescription.getText().toString();
		String grpowner = owner.getText().toString();
		String grpownerphone = phone.getText().toString();
		String grplocation = loc.getSelectedItem().toString();
		String grpprimary_goal = goal.getSelectedItem().toString();

		//System.out.println("Groupname is : " + groupname );
		
		
		grpname = grpname.replace(" ", "%20");
		grpdescription = grpdescription.replace(" ", "%20");
		grpowner = grpowner.replace(" ", "%20");
		grpownerphone = grpownerphone.replace(" ", "%20");
		grplocation = grplocation.replace(" ", "%20");
		grpprimary_goal = grpprimary_goal.replace(" ", "%20");
		j.execute("http://54.245.123.104/group.php?gName=" + grpname + "&gDes=" + grpdescription + "&gOwn="+ grpowner + "&gPhone=" + grpownerphone + "&gLoc="+ grplocation + "&gGoal=" + grpprimary_goal);
		finish();
	}

}
