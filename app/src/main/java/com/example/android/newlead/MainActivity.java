package com.example.android.newlead;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.newlead.main.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    private SlidingMenu mLeftMenu;

    private static final String TAG = "MainActivity";
    private ImageView mImageView;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                FragmentManager fragmentManager = getFragmentManager();
                mTransaction = fragmentManager.beginTransaction();
                mTransaction.replace(R.id.FL_fragment_layout, new MyFragment());
                mTransaction.commit();

            }
        }
    };
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        mImageView = (ImageView) findViewById(R.id.right_image);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "back", Toast.LENGTH_SHORT).show();
            }
        });
        mHandler.sendEmptyMessage(0);

    }


    public void toggleMenu(View view) {
        mLeftMenu.toggle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
