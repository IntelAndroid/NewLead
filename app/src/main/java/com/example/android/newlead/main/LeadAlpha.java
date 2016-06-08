package com.example.android.newlead.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.android.newlead.MainActivity;
import com.example.android.newlead.R;


public class LeadAlpha extends AppCompatActivity {
    private ImageView mImageView;
    private Animation mAnimation;

    Animation.AnimationListener mListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(new Intent(LeadAlpha.this, MainActivity.class));
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alpha_activity);
        mImageView = (ImageView) findViewById(R.id.iv_alpha_image);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mAnimation.setAnimationListener(mListener);
        mImageView.startAnimation(mAnimation);

    }
}
