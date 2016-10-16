package com.example.luo.calculator.adapter;

/**
 * Created by Luo on 2016/10/12.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.luo.calculator.fregment.CalFragment;
import com.example.luo.calculator.fregment.TransformFragment;

public class GuideFragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments = new Fragment[] { new CalFragment(),
            new TransformFragment()};

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

}
