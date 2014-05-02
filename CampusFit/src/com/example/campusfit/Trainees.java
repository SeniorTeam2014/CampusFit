package com.example.campusfit;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Trainees extends ListActivity {
	final CharSequence[] items = { "Bench Press", "Chest" };
	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}

	@Override
	public void onCreate(Bundle i) {
		setActionBar("My Trainees");
		super.onCreate(i);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, val);
		setListAdapter(adapter);
		final ListView lv = getListView();
		lv.setOnItemClickListener( new AdapterView.OnItemClickListener() { 
			@Override 
			public void onItemClick( AdapterView<?> av, View v, int pos, long id ) { 
				onListItemClick(lv, v, pos, id); 
			} 
		} );
		
		val.add("A trainee");

		// *** Ask server for list of matches based on personal info.
		// *** Populate arraylist with matches: val.add("Group HERE");

	}
	public void onListItemClick(ListView l, View v, int pos, long id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Assign Workout Templates");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// Do something with the selection
				
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
