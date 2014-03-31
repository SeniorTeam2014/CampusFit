package com.example.campusfit;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MatchmakerList extends ListActivity {

	static final String[] list = new String[] { "Goals", "Interests", "Time",
			"Gender", "Age" };

	
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
		super.onCreate(i);
		val.add("Goals");
		val.add("Interests");
		val.add("Time");
		val.add("Gender");
		val.add("Age");
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
		
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_LONG).show();
	}
	
	


}
