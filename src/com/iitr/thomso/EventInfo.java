package com.iitr.thomso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class EventInfo extends Activity implements OnClickListener{
	
	
	
	Button showOnMap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventinfo);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		EventInfo.this.getWindow().setAttributes(params);
		showOnMap = (Button) findViewById(R.id.callMap);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
		case R.id.callMap :
			//write your function here
			
		
		}
		
	}
			

}
