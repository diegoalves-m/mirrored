package com.diegoalvesmdev.joyjettest.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.fragments.MyLifeListFragment;
import com.diegoalvesmdev.joyjettest.fragments.PlacesListFragment;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabsAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.places);
        }
        return context.getString(R.string.my_life);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new PlacesListFragment();
        } else if (position == 1) {
            f = new MyLifeListFragment();
        }
        return f;
    }

}
