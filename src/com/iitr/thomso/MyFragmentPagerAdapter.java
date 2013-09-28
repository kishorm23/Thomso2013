package com.iitr.thomso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
	
	final int PAGE_COUNT = 4;
	
	/** Constructor of the class */
	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	/** This method will be invoked when a page is requested to create */
	@Override
	public Fragment getItem(int arg0) {
		Bundle data = new Bundle();
		switch(arg0){
		
			/** Android tab is selected */
			case 0:
				Day0 day0Fragment = new Day0();
				data.putInt("current_page", arg0+1);
				day0Fragment.setArguments(data);
				return day0Fragment;	
				
			/** Apple tab is selected */
			case 1:
				Day1 day1Fragment = new Day1();
				data.putInt("current_page", arg0+1);
				day1Fragment.setArguments(data);
				return day1Fragment;	
			case 2:
				Day2 day2Fragment = new Day2();
				data.putInt("current_page", arg0+1);
				day2Fragment.setArguments(data);
				return day2Fragment;	
			case 3:
				Day3 day3Fragment = new Day3();
				data.putInt("current_page", arg0+1);
				day3Fragment.setArguments(data);
				return day3Fragment;
		}
		
		return null;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
}
