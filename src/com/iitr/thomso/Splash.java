package com.iitr.thomso;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class Splash extends Activity{
	
	SharedPreferences somedata;
	String radio = "SharedString" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		somedata = getSharedPreferences(radio, 0);
		SharedPreferences.Editor editor = somedata.edit();
		editor.putString("filter", "All");
		editor.commit();
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					
						Intent openStartingPoint = new Intent(Splash.this , MainActivity.class);
						startActivity(openStartingPoint);
						
					
				}
			}
		};
		timer.start();

	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
