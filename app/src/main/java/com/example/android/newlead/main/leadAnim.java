package com.example.android.newlead.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.newlead.MainActivity;
import com.example.android.newlead.R;
import com.example.android.newlead.adapter.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 2016/5/30.
 */

public class leadAnim extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final String IS_FIRST_RUN = "isFirstRun";
    private TextView mtext;
    private ViewPager mviewPager;
    private List<View> mList;
    private static final String LEAD_CONFIG = "lead";
    private ImageView[] iocs = new ImageView[3];
    int[] pics = {R.drawable.ioc1, R.drawable.ioc2, R.drawable.ioc3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_image_activity);
        sharedPreferences();

    }

    public void sharedPreferences() {
        SharedPreferences preferences = getSharedPreferences(LEAD_CONFIG, Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean(IS_FIRST_RUN, true);
        if (!isFirstRun) {
            startActivity(new Intent(this, LeadAlpha.class));
            finish();
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(IS_FIRST_RUN, false);
            editor.commit();
            initview();
        }
    }

    public void initview() {
        mtext = (TextView) findViewById(R.id.TV_lead_skip);
        mviewPager = (ViewPager) findViewById(R.id.pager);
        mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(leadAnim.this, MainActivity.class));
                finish();
            }
        });


        mList = new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(pics[i]);
            mList.add(iv);
        }
        iocs[0] = (ImageView) findViewById(R.id.lead_icon1);
        iocs[1] = (ImageView) findViewById(R.id.lead_icon2);
        iocs[2] = (ImageView) findViewById(R.id.lead_icon3);
        iocs[0].setImageResource(R.drawable.off);
        mviewPager.addOnPageChangeListener(this);
        mviewPager.setAdapter(new PagerAdapter(mList));
        mviewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            mtext.setVisibility(View.VISIBLE);
        } else {
            mtext.setVisibility(View.GONE);
        }
        for (int i = 0; i < iocs.length; i++) {
            iocs[i].setImageResource(R.drawable.on);
        }
        iocs[position].setImageResource(R.drawable.off);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //官方提供的动画2
    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
