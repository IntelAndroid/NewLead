package com.example.android.newlead;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.newlead.adapter.NewAdapter;
import com.example.android.newlead.bean.getBean;
import com.example.android.newlead.main.WebActivity;
import com.example.android.newlead.main.getDataNew;

/**
 * Created by Android on 2016/6/5.
 */
public class MyFragment extends Fragment {
    private ListView mListView;
    private NewAdapter mNewAdapter;
    private getDataNew mGetDataNew;
    private WebActivity mWebActivity;
    private getBean.DataBean mDataBean;
    private static final String TAG = "MyFragment";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                mNewAdapter = new NewAdapter(mGetDataNew.GsonParse().getData(), getActivity());
                mListView.setAdapter(mNewAdapter);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view1, container, false);


        mListView = (ListView) view.findViewById(R.id.list_view_fragment);
        mListView.setOnItemClickListener(new MyClick());
        mGetDataNew = new getDataNew();
        /**
         * 传递给getDataNew类 的网址信息
         */
        new Thread() {
            @Override
            public void run() {

                mGetDataNew.getUri("1", "1", "1", "1", "20150520", "20");
                mHandler.sendEmptyMessage(1);
            }
        }.start();
        return view;
    }


    private class MyClick implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            //点击listview跳转到网页
            String html = mGetDataNew.GsonParse().getData().get(position).getLink();
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("link", html);
            startActivity(intent);


            Log.d(TAG, "onItemClick: " + mGetDataNew.GsonParse().getData().get(position).getLink());


            Toast.makeText(getActivity(), "點滴啦", Toast.LENGTH_SHORT).show();
        }
    }
}
