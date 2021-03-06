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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Templates extends ListActivity {

	ArrayList<String> val = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	Intent i1;
	boolean switchscreen;

	public void setActionBar(String heading) {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(heading);
		actionBar.show();
	}

	@Override
	public void onCreate(Bundle i) {
		i1 = new Intent(this, Exercises.class);
		switchscreen = true;
		showInstructions();

		setActionBar("My Workout Templates");
		super.onCreate(i);
		//val.add("Upper Body Strength Training");
		//val.add("Lower Body Strength Training");
		//val.add("Cardio Workout");
		//val.add("Core and Agility Training");
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
		
		//*** Ask server for user's templates
		// add template to list: 'val.add("template name here");'
		RetrieveTemplate j = new RetrieveTemplate();
		for(int k = 0 ; k < GlobalVariables.templates.size(); k++)
		{
			GlobalVariables.templates.remove(0);
		}
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
	}

	private void showInstructions() {
		// AlertDialogue for templates instructions
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Templates Instructions");

		alertDialogBuilder
		.setMessage("Create a new workout template through the options menu in titlebar. Press template to edit, or hold and press to delete.")
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
		switchscreen=false;
		final String item = (String) getListAdapter().getItem(position);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Delete Template?"); 
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				val.remove(item);
				adapter.notifyDataSetChanged();
				switchscreen=true;
				
				//*** Remove template from DB
				JSONFunctions2 j = new JSONFunctions2();
				
				try {
					j.execute("http://54.245.123.104/templateDelete.php?tName=" + item).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GlobalVariables.templates.remove(item);
				return;                  
			}  
		});  
		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switchscreen=true;
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
		if (switchscreen){
			try {
				i1.putExtra("templatename", item);
				startActivity(i1);
			} catch (ActivityNotFoundException e) {
				e.printStackTrace();
			}
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
		AlertDialog.Builder alert = new AlertDialog.Builder(this);                 
		alert.setTitle("Create Template");  
		alert.setMessage("Name your workout:"); 
		final EditText input = new EditText(this); 
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton) {  
				String template_name = input.getText().toString();
				val.add(template_name);
				JSONFunctions2 j = new JSONFunctions2();
				//*** Send template name back to server
				try {
					j.execute("http://54.245.123.104/templateCreate.php?uName=" + GlobalVariables.username + "&tName="+ template_name).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



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


}
