package com.example.android.newlead.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newlead.R;
import com.example.android.newlead.bean.getBean;
import com.example.android.newlead.main.ImageLoader;
import com.example.android.newlead.main.getDataNew;

import java.util.List;


/**
 * 适配文字图片
 * Created by Android on 2016/6/4.
 */
public class NewAdapter extends BaseAdapter {
    private static final String TAG = "NewAdapter";
  LayoutInflater mInflater;
    List<getBean.DataBean> mList;
    getDataNew getioc;
    public NewAdapter(List<getBean.DataBean> list , Context context) {
        mList = list;
        mInflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public getBean.DataBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.main, null);
            viewHolder.titles = (TextView) convertView.findViewById(R.id.titlest);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time_test);
            viewHolder.ioc = (ImageView) convertView.findViewById(R.id.ioc);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_praise);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        getBean.DataBean  bean =mList.get(position);
        Log.d(TAG, "getView: "+bean.getStamp());
        viewHolder.ioc.setImageResource(R.mipmap.ic_launcher);
        String url=bean.getIcon();//获取网址
        viewHolder.ioc.setTag(url);
        new ImageLoader().showImageByThread(viewHolder.ioc,url);//设置图片
        viewHolder.titles.setText(bean.getTitle());//设置标题
        viewHolder.content.setText(bean.getSummary());
        viewHolder.time.setText(bean.getStamp());

        Log.d(TAG, "getView: "+bean.getIcon().toString());

        return convertView;



    }
    public class ViewHolder {
        TextView titles;
        TextView content;
        TextView time;
        ImageView ioc;
        ImageView image;

    }

}
