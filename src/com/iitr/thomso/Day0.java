package com.iitr.thomso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockListFragment;

public class Day0 extends SherlockListFragment implements OnItemClickListener{
	
	String eventName[]={""} , eventTime[]={""} , eventVenue[]={""};
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.)
		
		List<HashMap<String , String  >> eventList = new ArrayList<HashMap<String , String >>();
		
		for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "" + eventName[i]);
            hm.put("venue","" + eventVenue[i]);
            hm.put("time","" + eventTime[i] );
            eventList.add(hm);
            
            // Keys used in Hashmap
            String[] from = { "name","venue","time" };
     
            // Ids of views in listview_layout
            //int[] to = { R.id.flag,R.id.txt,R.id.cur};
            
         // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(Day0.this, eventList, R.layout.listview_layout, from, to);
     
            // Getting a reference to listview of main.xml layout file
            ListView listView = ( ListView ) findViewById(R.id.listview);
     
            // Setting the adapter to the listView
            listView.setAdapter(adapter);
        }
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		
		
		
		
	}

}
