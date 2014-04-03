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
	final CharSequence[] how_to_measure={"Repetitions","Distance","Time"}; 
	String measure_option;
	String exercise_option;
	boolean measure_option_selected;
	boolean delete_lock;

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}
	
	@Override
	public void onCreate(Bundle i) {
		showInstructions();
		exercise_option="<blank>";
		measure_option_selected=false;
		delete_lock = true;

		String template;
		Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			template = extras.getString("templatename");
		}
		else
		{
			template="";
		}

		setActionBar("Exercises for "+template);
		super.onCreate(i);
		//val.add("Run");
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
		alertDialogBuilder.setTitle("Instructions");

		alertDialogBuilder
		.setMessage("Add new exercises to you workout template here!")
		.setCancelable(false)
		.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// functionality for button press 
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	public void askForDetails(String s){
		measure_option_selected=false;

		// Creating and Building the Dialog 
		AlertDialog.Builder measurements = new AlertDialog.Builder(this);
		measurements.setTitle("How will you measure '"+s+"'?");
		measurements.setSingleChoiceItems(how_to_measure, -1, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				switch(item)
				{
				case 0:
					// 1st option, "repetitions"
					measure_option="repetitions";
					measure_option_selected=true;
					break;
				case 1:
					// 2nd option, "distance"
					measure_option="distance";
					measure_option_selected=true;
					break;
				case 2:
					// 3rd option, "time"
					measure_option="time";
					measure_option_selected=true;
					break;
				default:
					break;
				}   
			}
		});
		measurements.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				if(measure_option_selected)
				{
					if (measure_option.equals("repetitions"))
					{
						askForReps();

					}
					else if (measure_option.equals("distance"))
					{
						askForDistance();
					}
					else if (measure_option.equals("time"))
					{
						askForTime();
					}
				}

				return;                  
			}  
		}); 
		measurements.show();	
	}


	public void askForReps()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
		alert.setTitle("Repetitions");  
		alert.setMessage("Enter the target number of repetitions"); 
		final EditText input = new EditText(this); 
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				exercise_option = input.getText().toString();
				System.out.println("> "+exercise_option);
				return;                  
			}  
		}); 
		alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(final DialogInterface dialog) {
				updateArrayAdapter(exercise_option);
			}
			});
		alert.show();

	}
	public void askForDistance()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
		alert.setTitle("Distance");  
		alert.setMessage("Enter the target distance"); 
		final EditText input = new EditText(this); 
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				exercise_option= input.getText().toString();
				System.out.println("> "+exercise_option);
				return;                  
			}  
		}); 
		alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(final DialogInterface dialog) {
				updateArrayAdapter(exercise_option);
			}
			});

		alert.show();

	}
	public void askForTime()
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
		alert.setTitle("Time");  
		alert.setMessage("Enter the target amount of time"); 
		final EditText input = new EditText(this); 
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				exercise_option= input.getText().toString();
				System.out.println("> "+exercise_option);
				return;                  
			}  
		}); 
		alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(final DialogInterface dialog) {
				updateArrayAdapter(exercise_option);
			}
			});
		alert.show();

	}


	public void onLongListItemClick(View v, int position, long id) 
	{   
		delete_lock = false;
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
				delete_lock=true;
				return;   
			}
		});
		alert.show();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item+" exercise", Toast.LENGTH_LONG).show();

		// when template is pressed, add items to it
		//if (delete_lock)
		//{
		//	askForDetails(item);
		//}
		//updateArrayAdapter(exercise_option);

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercises_menu, menu);
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
					askForDetails(s);
					//System.out.println(">>> "+exercise_option);

					val.add("'"+s+"'");
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

	public void updateArrayAdapter(String options)
	{
		for (String s : val)
		{
			//System.out.println("found string: "+s);
			//System.out.println("option: "+options);
			if (s.equals(exercise_option));
			
				//System.out.println("!!! "+val.indexOf(s));
				int x = val.indexOf(s);
				val.set(x, s+" for "+options);
		}

	}




}


