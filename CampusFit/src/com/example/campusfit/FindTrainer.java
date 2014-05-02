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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FindTrainer extends ListActivity {

	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	public void onCreate(Bundle i) {
		
		setActionBar("Find A Trainer");
		showInstructions();
		super.onCreate(i);
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, val);
		//setListAdapter(adapter);
		final ListView lv = getListView();
		lv.setOnItemClickListener( new AdapterView.OnItemClickListener() { 
			@Override 
			public void onItemClick( AdapterView<?> av, View v, int pos, long id ) { 
				onListItemClick(lv, v, pos, id); 
				//return false; 
			} 
		} );
		//val.add("A trainer");
		
		//*** Ask server for list of matches based on personal info.
		//*** Populate arraylist with matches: val.add("USERNAME HERE");

	}
	
	private void showInstructions() {
		// AlertDialogue for templates instructions
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Find a Trainer");

		alertDialogBuilder
		.setMessage("The trainer finder will automatically suggest trainers based on your profile. Press on a username to see information.")
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				setActionBar("Find A Trainer -- Results");
				// functionality for button press 
				RetrieveTrainer t = new RetrieveTrainer();
				t.execute("http://54.245.123.104/findTrainer.php?tSpec=running");
				SystemClock.sleep(300);
				for(int i = 0 ; i < GlobalVariables.trainers.size(); i++)
					val.add(GlobalVariables.trainers.get(i));
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

		//*** Ask server for trainer information and display in message
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Find a Trainer");
		RetrieveTrainerInfo t = new RetrieveTrainerInfo();
		t.execute("http://54.245.123.104/profile.php?uName=" + item);
		SystemClock.sleep(300);
		alertDialogBuilder
		.setMessage("Name: " + GlobalVariables.tname + "\nAge: " + GlobalVariables.tage + "\nPhone: " + GlobalVariables.tphone )
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
