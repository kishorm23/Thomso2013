package com.iitr.thomso;

import android.os.Bundle;




import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


public class MainActivity extends SherlockFragmentActivity {
	ActionBar mActionBar;
	ViewPager mPager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /** Getting a reference to action bar of this activity */
        mActionBar = getSupportActionBar();
        
        /** Set tab navigation mode */
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        /** Getting a reference to ViewPager from the layout */
        mPager = (ViewPager) findViewById(R.id.pager);
        
        /** Getting a reference to FragmentManager */
        FragmentManager fm = getSupportFragmentManager();      
        
        /** Defining a listener for pageChange */
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
        	@Override
        	public void onPageSelected(int position) {        		
        		super.onPageSelected(position);
        		mActionBar.setSelectedNavigationItem(position);        		
        	}
        	
        };
        
        /** Setting the pageChange listner to the viewPager */
        mPager.setOnPageChangeListener(pageChangeListener);
        
        /** Creating an instance of FragmentPagerAdapter */
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fm);
        
        /** Setting the FragmentPagerAdapter object to the viewPager object */
        mPager.setAdapter(fragmentPagerAdapter);

        mActionBar.setDisplayShowTitleEnabled(true);
        
        /** Defining tab listener */
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mPager.setCurrentItem(tab.getPosition());
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			}
		};

		/** Creating Android Tab */
        Tab tab = mActionBar.newTab()
                           .setText("day 0")
                           .setTabListener(tabListener);
        
        mActionBar.addTab(tab);

        /** Creating Apple Tab */
        tab = mActionBar.newTab()
                       .setText("day 1")
                       .setTabListener(tabListener);                               

        mActionBar.addTab(tab);  
        
        /** Creating Apple Tab */
        tab = mActionBar.newTab()
                       .setText("day 2")
                       .setTabListener(tabListener);                               

        mActionBar.addTab(tab);
        
        /** Creating Apple Tab */
        tab = mActionBar.newTab()
                       .setText("day 3")
                       .setTabListener(tabListener);                               

        mActionBar.addTab(tab); 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getSherlock().getMenuInflater();
        inflater.inflate(R.menu.activity_main,  menu);
        return true;		
			
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	   super.onOptionsItemSelected(item);
    	   
    	   switch(item.getItemId())
    	   {
    	   case R.id.menu_map :
    		 Toast.makeText(MainActivity.this,
    			          "Map menu clicked.", Toast.LENGTH_LONG)
    			          .show();
    		   break;
    	   case R.id.menu_instructions :
    		   Toast.makeText(MainActivity.this,
    	    			 "instuctions.", Toast.LENGTH_LONG)
    	    			 .show();
    		   break;
    	   case R.id.menu_aboutUs :  
    		   Toast.makeText(MainActivity.this,
    	    			 "about us clicked.", Toast.LENGTH_LONG)
    	    			 .show();
    		   break;
    	   }
 
        return true;
 
    }
    
    
}