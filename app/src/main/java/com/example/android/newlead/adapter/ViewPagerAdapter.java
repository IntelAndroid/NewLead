package com.example.android.newlead.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.view.*;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Android on 2016/5/31.
 */
public class ViewPagerAdapter extends android.support.v4.view.PagerAdapter {
private List<View>mList;
private List<String> mStrings;

    public ViewPagerAdapter(List<View> list, List<String> strings) {
        mList = list;
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings.get(position);
    }
}
