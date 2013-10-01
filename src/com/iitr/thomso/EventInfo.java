package com.iitr.thomso;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class EventInfo extends Activity {
	
	private TextView tv_name ,tv_venue , tv_description ,tv_time , tv_coordinators ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventinfo);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		EventInfo.this.getWindow().setAttributes(params);
		Bundle b = getIntent().getExtras();
		
		tv_name = (TextView)findViewById(R.id.textName);
		tv_venue = (TextView) findViewById(R.id.textVenue);
		tv_description = (TextView) findViewById(R.id.textDescription);
		tv_time = (TextView) findViewById(R.id.textTime);
		tv_coordinators = (TextView) findViewById(R.id.textCoordinators);
		
		String event_name=b.getString("event_name");
		String venue = b.getString("venue");
		String description = b.getString("description");
		String time = b.getString("time");
		String coordinators = b.getString("coordinators");
		
		tv_name.setText(event_name);
		tv_venue.setText(venue);
		tv_description.setText(description);
		tv_time.setText(time);
		tv_coordinators.setText(coordinators);
    	
	}

}
			


