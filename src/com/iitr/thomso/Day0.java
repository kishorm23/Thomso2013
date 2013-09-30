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
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListFragment;

@SuppressLint("ValidFragment")
public class Day0 extends SherlockListFragment {
	
	SharedPreferences somedata;
	String radio = "SharedString";
	
	//Retrieve from database
	String[][] Data;
	Spinner spinner;
	 private String array_spinner[];
	LinearLayout llayout;
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
		SimpleAdapter adapter = null;
		somedata = getSherlockActivity().getSharedPreferences(radio, 0);
		String dataReturned = somedata.getString("filter", "All");
		if(dataReturned=="All") Data = myDbHelper.getEventbyDay(0);
		else Data = myDbHelper.getEventbyType(dataReturned, 0);
		if(Data.length!=0)
		{
		for(int i=0;i<Data.length;i++){
			String x = null;
			try
			{x = ConvertTo(Data[i][4]);
			if(Data[i][5]!=null) x=x+" - "+ConvertTo(Data[i][5]);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
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
            // R.layout.listview_layout defines the layout of each ite
            adapter = new SimpleAdapter(getActivity().getBaseContext(), 
            				eventList, R.layout.listview_layout, from, to);
     
            // Setting the adapter to the listView
		}
		}
		else
		{
			HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("name", "" + "");
            hm.put("venue","" + "No event in this catagory");
            hm.put("time","" + "" );
            eventList.add(hm);
            String[] from = { "name","venue","time" };
            
            //Ids of views in listview_layout
            int[] to = { R.id.eName,R.id.eVenue,R.id.eTime};
            
            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each ite
            adapter = new SimpleAdapter(getActivity().getBaseContext(), 
            				eventList, R.layout.listview_layout, from, to);
            
			
		}
            setListAdapter(adapter);
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
	        if(Data.length==0) getListView().setEnabled(false);
	 
	    }
	 
	 
		public String ConvertTo(String time)
		{
			Log.i("DEBUG_TIME","At "+ time );
			if(time.charAt(0)=='9') time="0"+time;
			String hours = (String) time.subSequence(0, 2);
			String minutes = (String) time.subSequence(3, 5);
			
			int hour = Integer.parseInt(hours);
			int minute = Integer.parseInt(minutes);
			
			String toReturn;
			if(hour>12) toReturn=String.valueOf(hour-12);
			else if(hour<10) toReturn = String.valueOf(hour);
			else toReturn=hours;
			if(minute<10) toReturn=toReturn+":"+"0"+minute;
			else toReturn=toReturn+":"+minute;
			if(hour>12) toReturn = toReturn + " PM";
			else toReturn=toReturn+"AM";
			Log.i("DEBUG_TIME","hour: "+hour+" minutes: "+minutes+" Converted String: "+toReturn);
			return toReturn;
		}
	
	
}
