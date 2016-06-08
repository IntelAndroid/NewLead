package com.example.android.newlead.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Android on 2016/5/30.
 */

public class PagerAdapter extends android.support.v4.view.PagerAdapter {
    private List<View> mlist;

    public PagerAdapter(List<View> mlist) {
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        container.addView(mlist.get(position));
        return mlist.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mlist.get(position));
    }
}
