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
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;

public class Day1 extends SherlockListFragment {
	
	//Retrieve from database
	String eventName[]={""} , eventTime[]={""} 
	, eventVenue[]={""};
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<HashMap<String , String  >> eventList = new ArrayList<HashMap<String , String >>();
		
		for(int i=0;i<eventName.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "" + eventName[i]);
            hm.put("venue","" + eventVenue[i]);
            hm.put("time","" + eventTime[i] );
            eventList.add(hm);
            
            // Keys used in Hashmap
            String[] from = { "name","venue","time" };
     
            //Ids of views in listview_layout
            int[] to = { R.id.eName,R.id.eVenue,R.id.eTime};
            
            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), 
            				eventList, R.layout.listview_layout, from, to);
     
            // Setting the adapter to the listView
            setListAdapter(adapter);   
        }
		return super.onCreateView(inflater, container, savedInstanceState); 
	}
	
	 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onActivityCreated(savedInstanceState);
	 
	        OnItemClickListener listener = new OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
	                Toast.makeText( getActivity().getBaseContext()  , "Clicked " +
	                				eventName[position] , Toast.LENGTH_SHORT).show();
	               
	            }
	        };
	 
	        getListView().setOnItemClickListener(listener);
	 
	    }
	
}
