package com.example.campusfit;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MatchmakerList extends ListActivity {

	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}

	@Override
	public void onCreate(Bundle i) {
		setActionBar("Match Maker");
		showInstructions();
		super.onCreate(i);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, val);
		
		final ListView lv = getListView();
		lv.setOnItemClickListener( new AdapterView.OnItemClickListener() { 
			@Override 
			public void onItemClick( AdapterView<?> av, View v, int pos, long id ) { 
				onListItemClick(lv, v, pos, id); 
				//return false; 
			} 
		} );
		//val.add("A user");

		//*** Ask server for list of matches based on personal info.
		//*** Populate arraylist with matches: val.add("USERNAME HERE");


	}

	private void showInstructions() {
		// AlertDialogue for templates instructions
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Match Maker");

		LinearLayout layout = new LinearLayout(this.getBaseContext());
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText interestbox = new EditText(this.getBaseContext());
		interestbox.setHint("Enter your interests");
		layout.addView(interestbox);
		
		alertDialogBuilder.setView(layout);
		
		alertDialogBuilder
		.setMessage("The match maker will suggest other users based on your profile and interests. Press on a username to see information.")
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				setActionBar("Match Maker -- Results");
				//*** send query to server
				RetrieveMatch m = new RetrieveMatch();
				m.execute("http://54.245.123.104/match.php?Interest=running&uName="+GlobalVariables.username);
				SystemClock.sleep(300);
				for(int k = 0; k < GlobalVariables.matches.size(); k++)
					val.add(GlobalVariables.matches.get(k));
				setListAdapter(adapter);
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}



	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_LONG).show();

		//*** Ask server for user information and display in message
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Match Maker");
		RetrieveMatchInfo m = new RetrieveMatchInfo();
		m.execute("http://54.245.123.104/profile.php?uName=" + item);
		SystemClock.sleep(300);
		alertDialogBuilder
		.setMessage("Name: " + GlobalVariables.mname + "\nAge: " + GlobalVariables.mage + "\nPhone: " + GlobalVariables.mphone)
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// functionality for button press 
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}




}
