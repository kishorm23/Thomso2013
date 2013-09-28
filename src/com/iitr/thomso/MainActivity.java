package com.iitr.thomso;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;


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
		SubMenu map = menu.addSubMenu("Map");
		map.getItem().setShowAsAction(
				MenuItem.SHOW_AS_ACTION_NEVER);
		SubMenu instructions = menu.addSubMenu("Instructions");
		instructions.getItem().setShowAsAction(
				MenuItem.SHOW_AS_ACTION_NEVER);
		SubMenu aboutUs = menu.addSubMenu("About Us");
		aboutUs.getItem().setShowAsAction(
				MenuItem.SHOW_AS_ACTION_NEVER);
		
		return true;	
    }
    
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	 
//        super.onOptionsItemSelected(item);
// 
//        switch(item.getItemId()){
//        
//        case R.id.menu_map :
//        	
//        	break;
//        	
//        case R.id.menu_instructions :
//        	
//        	break;
//        	
//        case R.id.menu_aboutUs :
//        	
//        	break;
//        
//        	
//       
//        }
//        return true;
// 
//    }
    
    
}