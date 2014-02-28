package com.example.campusfit;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FindTrainer extends ListActivity {

	static final String[] list = new String[] { "Proximity", "Goal", "Gender"};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.matchmaker, list));
		final ListView listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
					
				
			}

		});

	}

}
