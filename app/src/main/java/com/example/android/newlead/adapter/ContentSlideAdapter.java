package com.example.android.newlead.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Android on 2016/5/31.
 */
public class ContentSlideAdapter extends FragmentPagerAdapter {
    private List<String> mStrings;
    private List<Fragment> mFragments;

    public ContentSlideAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mStrings) {
        super(fm);
        this.mStrings = mStrings;
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * 标题栏
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings.get(position);
    }
}
