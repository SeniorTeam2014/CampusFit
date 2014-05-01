package com.example.campusfit;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class FindGroup extends ListActivity {

	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	String[] location = { "West Lafayette" };
	String[] primary_goals = {"Strength Training", "Weight Loss", "Social Exercising"};

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}

	@Override
	public void onCreate(Bundle i) {
		setActionBar("Find A Group");
		showInstructions();
		super.onCreate(i);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, val);
		setListAdapter(adapter);
		final ListView lv = getListView();
		lv.setOnItemClickListener( new AdapterView.OnItemClickListener() { 
			@Override 
			public void onItemClick( AdapterView<?> av, View v, int pos, long id ) { 
				onListItemClick(lv, v, pos, id); 
				//return false; 
			} 
		} );
		val.add("A Group");

		//*** Ask server for list of matches based on personal info.
		//*** Populate arraylist with matches: val.add("Group HERE");


	}

	private void showInstructions() {
		
	    final ArrayAdapter<String> adp = new ArrayAdapter<String>(this.getBaseContext(),
	            android.R.layout.simple_spinner_item, location);
	    final ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this.getBaseContext(),
	            android.R.layout.simple_spinner_item, primary_goals);
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Search for a group:");
		
		LinearLayout layout = new LinearLayout(this.getBaseContext());
		layout.setOrientation(LinearLayout.VERTICAL);

		final EditText titleBox = new EditText(this.getBaseContext());
		titleBox.setHint("Enter a group description");
		layout.addView(titleBox);

		final Spinner sp = new Spinner(this.getBaseContext());
	    sp.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	    sp.setAdapter(adp);
	    layout.addView(sp);
	    
		final Spinner sp2 = new Spinner(this.getBaseContext());
	    sp2.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	    sp2.setAdapter(adp2);
	    layout.addView(sp2);
	    
		dialog.setView(layout);
		dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				setActionBar("Find A Group -- Results");
				// functionality for button press 
				//*** send info to server here?
			}
		});
		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				return;   
			}
		});
		dialog.show();
	}



	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		//*** request group info from server
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_LONG).show();


		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Find A Group");

		//*** set the message to group info
		alertDialogBuilder
		.setMessage("Group INFORMATION HERE")
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
