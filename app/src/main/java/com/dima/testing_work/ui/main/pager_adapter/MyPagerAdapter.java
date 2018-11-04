package com.dima.testing_work.ui.main.pager_adapter;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dima.testing_work.ui.main.fragments.loading_fragment.Loading;
import com.dima.testing_work.ui.main.fragments.saved_fragment.Saved;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new Loading();
        }
        else if (position == 1)
        {
            fragment = new Saved();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Loading";
        }
        else if (position == 1)
        {
            title = "Saved";
        }
        return title;
    }
}