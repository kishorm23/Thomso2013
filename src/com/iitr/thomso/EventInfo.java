package com.iitr.thomso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class EventInfo extends Activity {
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventinfo);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		EventInfo.this.getWindow().setAttributes(params);
		Bundle b = getIntent().getExtras();
		
		String event_name=b.getString("event_name");
		String venue = b.getString("venue");
		String description = b.getString("description");
		String time = b.getString("time");
		String coordinators = b.getString("coordinators");
    	
	}

}
			


