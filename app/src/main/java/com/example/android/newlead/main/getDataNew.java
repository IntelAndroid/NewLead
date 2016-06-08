package com.example.android.newlead.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;


import com.example.android.newlead.bean.getBean;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Android on 2016/6/4.
 */
public class  getDataNew {
    /**
     * 拼接网址
     */
    private static final String TAG = "getDataNew";
    public static final String VER = "ver";
    public static final String SUBID = "subid";
    public static final String DIR = "dir";
    public static final String NID = "nid";
    public static final String STAMP = "stamp";
    public static final String CNT = "cut";
    public static final String NETIP = "118.244.212.82";
    public static final String NETPATH = "http://" + NETIP + ":9092/newsClient";
    public static final String NETPATH_NEWS = NETPATH + "/news_list";
    private String mMUrls;
    private BufferedReader mReader;
    private HttpURLConnection mConnection;
    public String mBufferData;
    public Bitmap mBitmap;
    private getBean mGsonbean;


    public String getUri(String ver, String subid, String dir, String nid, String stamp,
                         String cnt) {
        mMUrls = Uri.parse(NETPATH_NEWS).buildUpon()
                .appendQueryParameter(VER, ver)
                .appendQueryParameter(SUBID, subid)
                .appendQueryParameter(DIR, dir)
                .appendQueryParameter(NID, nid)
                .appendQueryParameter(STAMP, stamp)
                .appendQueryParameter(CNT, cnt)
                .build().toString();
        Log.d(TAG, "getUri: " + mMUrls);

        /**
         * 获取网络数据
         */
        try {
            URL urls = new URL(mMUrls);

            mConnection = (HttpURLConnection) urls.openConnection();
            mConnection.setRequestMethod("GET");
            mConnection.setReadTimeout(5000);
            mConnection.connect();
            StringBuffer buffer = new StringBuffer();
            InputStream inputStream = mConnection.getInputStream();
            mReader = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while ((str = mReader.readLine()) != null) {
                buffer.append(str);
            }
            mBufferData = buffer.toString();
            Log.d(TAG, "getData: " + mBufferData);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return mBufferData;


    }

    /**
     * 解析Gson
     * @return
     */
    public getBean GsonParse() {
        Gson gson = new Gson();
        mGsonbean = gson.fromJson(mBufferData, getBean.class);
//        Log.d(TAG, "GsonParse: "+mBufferData);
//        Log.d(TAG, "GsonParse:000000 "+gsonbean.getMessage()+gsonbean.getData().get(0).getSummary());
        return mGsonbean;
    }

    public Bitmap getImage(String s) {


        try {
            URL ioc = new URL(s);
            try {
                HttpURLConnection coon = (HttpURLConnection) ioc.openConnection();
                coon.setReadTimeout(5000);
                coon.connect();
                InputStream inputStream = coon.getInputStream();
                BufferedInputStream buffer = new BufferedInputStream(inputStream);
                mBitmap = BitmapFactory.decodeStream(buffer);
                inputStream.close();
                buffer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return mBitmap;
    }


}
