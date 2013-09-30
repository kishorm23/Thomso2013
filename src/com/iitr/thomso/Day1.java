package com.iitr.thomso;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;

@SuppressLint("ValidFragment")
public class Day1 extends SherlockListFragment {
	
	String[][] Data;
	SharedPreferences somedata;
	String radio = "SharedString";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<HashMap<String , String  >> eventList = new ArrayList<HashMap<String , String >>();
		DatabaseHelper myDbHelper = new DatabaseHelper(getSherlockActivity().getBaseContext());
		try{
			myDbHelper.createDataBase();
			}
		catch (IOException ioe) {throw new Error("Unable to create database");}
 
		try{
				myDbHelper.openDataBase();
			}
		catch(SQLException sqle){
				throw sqle;
			}
		somedata = getSherlockActivity().getSharedPreferences(radio, 0);
		String dataReturned = somedata.getString("filter", "All");
		if(dataReturned=="All") Data = myDbHelper.getEventbyDay(1);
		else Data = myDbHelper.getEventbyType(dataReturned, 1);
		
		if(Data.length!=0)
		{
		for(int i=0;i<Data.length;i++){
			String x=Data[i][4];
			Log.i("DEBUG_TIME","Current Event:"+Data[i][2]);
			x=ConvertTo(Data[i][4]);
			if(Data[i][5]!=null) x=x+" - "+ConvertTo(Data[i][5]);
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "" + Data[i][2]);
            hm.put("venue","" + "Venue: "+Data[i][3]);
            hm.put("time","" +  x );
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
		}
		else
		{
			 HashMap<String, String> hm = new HashMap<String,String>();
	            hm.put("name", "");
	            hm.put("venue","" + "No events in this catagory");
	            hm.put("time","" +  "" );
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
	                				Data[position][2] , Toast.LENGTH_SHORT).show();
	               
	            }
	        };
	 
	        getListView().setOnItemClickListener(listener);
	 
	    }
	 
		public String ConvertTo(String time)
		{
			if(time.length()==0) return null;
			if(time.length()==4) time="0"+time;
			String hours = (String) time.subSequence(0, 2);
			String minutes = (String) time.subSequence(3, 5);
			
			
			int hour = Integer.parseInt(hours);
			int minute = Integer.parseInt(minutes);
			
			String toReturn;
			if(hour>12) toReturn=String.valueOf(hour-12);
			else toReturn=hours;
			if(minute<10) toReturn=toReturn+":"+"0"+minute;
			else toReturn=toReturn+":"+minute;
			if(hour>12) toReturn = toReturn + " PM";
			else toReturn=toReturn+" AM";
			Log.i("DEBUG_TIME","hour: "+hour+" minutes: "+minutes+" Converted String: "+toReturn);
			return toReturn;
		}
	
}
