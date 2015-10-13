package com.incture.leaveme.adapter;

/**
 * Created by JANANI.N on 03-09-2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.incture.leaveme.fragment.All;
import com.incture.leaveme.fragment.Approved;
import com.incture.leaveme.fragment.Pending;


public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Top Rated fragment activity
                return new All();

            case 1:
                // Games fragment activity
                // return new ViewsFragment();
                return new Approved();
            case 2:
                // Movies fragment activity
                return new Pending();



//		case 4:
//
//			return new SettingsFragment();

        }

        return null;
    }



    @Override
    public int getCount() {
        return 3;
    }
}