package com.example.campusfit;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Workouts extends ListActivity {
	
	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		setActionBar("Workouts");
		showInstructions();
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, val);
		setListAdapter(adapter);
		ListView lv = getListView();

		//*** Ask server for user's templates
		// add template to list: 'val.add("template name here");'
		RetrieveTemplate j = new RetrieveTemplate();
		/*
		try {
			System.out.println(GlobalVariables.username);
			j.execute("http://54.245.123.104/templateTemp.php?uName=" + GlobalVariables.username).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int k = 0; k < GlobalVariables.templates.size(); k++)
			val.add(GlobalVariables.templates.get(k));
			*/
		val.add("test");
		
	}
	
	
	private void showInstructions() {
		// AlertDialogue for templates instructions
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Workout Instructions");

		alertDialogBuilder
		.setMessage("Press a template to add to your calendar")
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item+" template", Toast.LENGTH_LONG).show();

		// when template is pressed, create calendar event
		
		String title="", location="", description="";
		//*** set title, location, description of template
		title = "Campus Fit Workout";
		location = "";
		description = item;
		
		Intent intent = new Intent(Intent.ACTION_INSERT);
		intent.setData(CalendarContract.Events.CONTENT_URI);		
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra(Events.TITLE, title);
		intent.putExtra(Events.EVENT_LOCATION, location);
		intent.putExtra(Events.DESCRIPTION, description);
		startActivity(intent); 
	}
	
	

}
