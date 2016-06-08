package com.example.android.newlead.main;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.android.newlead.R;
import com.example.android.newlead.bean.getBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebActivity extends AppCompatActivity {
    private static WebView mWebView;
    private static ProgressBar mProgressBar;
    private static String mBufferData;
    private static final String TAG = "WebActivity";

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };

    /**
     * 跳转到WebView加载网页
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        String Urls = getIntent().getStringExtra("link");
        mWebView.loadUrl(Urls);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        mProgressBar.setVisibility(View.VISIBLE);
        mHandler.sendEmptyMessage(0);
        // new MyAsycnTast().execute(Urls);

    }

//    static class MyAsycnTast extends AsyncTask<String, Void, WebView> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mProgressBar.setVisibility(View.VISIBLE);
//
//        }
//
//        @Override
//        protected void onPostExecute(WebView webView) {
//            super.onPostExecute(webView);
//            mProgressBar.setVisibility(View.GONE);
//
//
//            mWebView.loadData(, "text/html; charset=gb2312", null);//这种写法可以正确解码
//        }
//
//        @Override
//        protected WebView doInBackground(String... params) {
//            String url = params[0];

//            URLConnection connection;
//            final StringBuffer buffer;
//            BufferedReader reader = null;
//            try {
//                connection = new URL(url).openConnection();
//
//                connection.setReadTimeout(5000);
//                connection.connect();
//
//                buffer = new StringBuffer();
//                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//
//                String str;
//                while ((str = reader.readLine()) != null) {
//                    buffer.append(str);
//                }
//                mBufferData = buffer.toString();
//                Log.d(TAG, "doInBackground: " + mBufferData);
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//            return mWebView;
//        }
//    }

}
