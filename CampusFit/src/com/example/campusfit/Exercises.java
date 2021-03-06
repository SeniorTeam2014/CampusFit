package com.example.campusfit;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
	String exercise_selected;
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
		exercise_selected="";
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

		//*** Ask server for user's exercises
		// add exercise to list: 'val.add("exercise name here");'
		RetrieveExercise j = new RetrieveExercise();
		
		try {
			GlobalVariables.exercises = new ArrayList<String>();
			j.execute("http://54.245.123.104/exercise.php?tName=" +template).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SystemClock.sleep(300);
		for(int k = 0; k < GlobalVariables.exercises.size(); k++)
			val.add(GlobalVariables.exercises.get(k));
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
		final String name = s;
		//System.out.println("!!!!!!!!!!String s: "+s);
		
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
						askForReps(name);

					}
					else if (measure_option.equals("distance"))
					{
						askForDistance(name);
					}
					else if (measure_option.equals("time"))
					{
						askForTime(name);
					}
				}

				return;                  
			}  
		}); 
		measurements.show();	
	}


	public void askForReps(final String name)
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
				updateArrayAdapter(name);
			}
		});
		alert.show();

	}
	public void askForDistance(final String name)
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
				updateArrayAdapter(name);
			}
		});

		alert.show();

	}
	public void askForTime(final String name)
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
				updateArrayAdapter(name);
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
				JSONFunctions2 j = new JSONFunctions2();
				String[] splited = item.split("\\s+");
				try {
					j.execute("http://54.245.123.104/removeExercise.php?eName=" +splited[0]).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//*** delete exercise from DB
				  
				
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

	}

	public void updateArrayAdapter(String name_of_exercise)
	{
		System.out.println("name_of_exercise "+name_of_exercise);
		for (String s : val)
		{
			//System.out.println("s: "+s);
			//System.out.println("exercise_option: " +exercise_option);
			if (s.equals("'"+name_of_exercise+"'")){

				int x = val.indexOf(s);
				val.set(x, s+" for "+exercise_option);
				
				//*** Send exercise string back to server
				JSONFunctions2 j = new JSONFunctions2();
				exercise_option = exercise_option.replace(" ", "%20");
				name_of_exercise = name_of_exercise.replace(" ", "%20");
				try {
					System.out.println("http://54.245.123.104/exerciseCreate.php?uName=" + GlobalVariables.username +"&tName="+ GlobalVariables.templates.get(0) + "&eName=" +name_of_exercise + "&eDes=" + exercise_option);
					j.execute("http://54.245.123.104/exerciseCreate.php?uName=" + GlobalVariables.username +"&tName="+ GlobalVariables.templates.get(0) + "&eName=" +name_of_exercise + "&eDes=" + exercise_option).get();
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




}


