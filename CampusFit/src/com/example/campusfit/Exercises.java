package com.example.campusfit;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Exercises extends ListActivity {
	
	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	@Override
	public void onCreate(Bundle i) {
		showInstructions();

		setActionBar("My Workout Templates");
		super.onCreate(i);
		val.add("Run");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, val);
		setListAdapter(adapter);
		ListView lv = getListView();
		lv.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() { 
			@Override 
			public boolean onItemLongClick( AdapterView<?> av, View v, int pos, long id ) { 
				onLongListItemClick(v, pos, id); 
				return false; 
			} 
		} );

	}

	private void showInstructions() {
		// AlertDialogue for templates instructions
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Templates Instructions");

		alertDialogBuilder
		.setMessage("Create a new exercise through the options menu in titlebar. Press template to edit, or hold and press to delete.")
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// functionality for button press 
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	public void onLongListItemClick(View v, int position, long id) 
	{   
		final String item = (String) getListAdapter().getItem(position);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Delete Exercise?"); 
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				val.remove(item);
				adapter.notifyDataSetChanged();
				return;                  
			}  
		});  

		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				return;   
			}
		});
		alert.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item+" template", Toast.LENGTH_LONG).show();
		
		// when template is pressed, add items to it
		
		//create a new screen with some options (type of exercise, repetitions, time, distance)
		try {
			Intent i1 = new Intent(this, Exercises.class);
			startActivity(i1);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}	
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.templates_menu, menu);
		return true;
	}

	// menu item has been selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// check if create or delete was pressed
		switch (item.getItemId()) {
		case R.id.create:
			AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
			alert.setTitle("Create Exercise");  
			alert.setMessage("Name of exercise:"); 
			final EditText input = new EditText(this); 
			alert.setView(input);
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
				public void onClick(DialogInterface dialog, int whichButton) {  
					String s = input.getText().toString();
					val.add(s);
					return;                  
				}  
			});  

			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					return;   
				}
			});
			alert.show();
			return true;



		default: return true;
		}

	}


}
	

