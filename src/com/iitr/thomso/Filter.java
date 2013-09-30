package com.iitr.thomso;
import java.security.PublicKey;

import com.iitr.thomso.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;


public class Filter extends Activity {
	RadioGroup rg;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_layout);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		Filter.this.getWindow().setAttributes(params);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		btn = (Button) findViewById(R.id.button1);
		
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SharedPreferences somedata;
				String radio = "SharedString";
				somedata = getSharedPreferences(radio, 0);
				SharedPreferences.Editor editor = somedata.edit();
				switch (rg.getCheckedRadioButtonId()) {
				case R.id.radio0:
				{
					editor.putString("filter","All" );
					break;
				}
				case R.id.radio1:
				{	
					editor.putString("filter","pronites" );
					break;
				}
				case R.id.radio2:
				{
					editor.putString("filter","competition" );
					break;
				}
				case R.id.radio3:
				{
					editor.putString("filter","workshop" );
					break;
				}
				case R.id.radio4:
				{
					editor.putString("filter","street performance" );
					break;
				}
				case R.id.radio5:
				{
					editor.putString("filter","show/guest lecture" );
					break;
				}
				}
				editor.commit();
				Intent intent = new Intent(Filter.this , MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}
	

}
