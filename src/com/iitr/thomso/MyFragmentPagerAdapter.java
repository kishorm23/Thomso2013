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
				Day0 appleaFragment = new Day0();
				data.putInt("current_page", arg0+1);
				appleaFragment.setArguments(data);
				return appleaFragment;	
				
			/** Apple tab is selected */
			case 1:
				Day1 appleFragment = new Day1();
				data.putInt("current_page", arg0+1);
				appleFragment.setArguments(data);
				return appleFragment;	
			case 2:
				Day2 apple2Fragment = new Day2();
				data.putInt("current_page", arg0+1);
				apple2Fragment.setArguments(data);
				return apple2Fragment;	
			case 3:
				Day3 apple3Fragment = new Day3();
				data.putInt("current_page", arg0+1);
				apple3Fragment.setArguments(data);
				return apple3Fragment;
		}
		
		return null;
	}

	/** Returns the number of pages */
	@Override
	public int getCount() {		
		return PAGE_COUNT;
	}
	
}
