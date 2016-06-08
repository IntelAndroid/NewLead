package com.example.android.newlead.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网络图片
 * 使用LruCache进行缓存
 * Created by Administrator on 2016/6/3 0003.
 */
public class ImageLoader {
    private ListView                 mListView;
    private ImageView                mImageView;
    private String                   imageUrl;
    //private Set<NewsAsncTask>        mTask;
    //    创建Cache
    private LruCache<String, Bitmap> mCache;

    /**
     * 加载图片的第一种方式,使用线程Thread
     *
     * @param imageView 图片
     * @param url       该图片对应的urL
     */
    public void showImageByThread(ImageView imageView, final String url) {
        mImageView = imageView;
        imageUrl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Bitmap bitmap = getBitmapFromUrl(url);
                    Message message = Message.obtain();
//                    将message的obj设置为bitmap
                    message.obj = bitmap;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 创建一个通过Url转化为Bitmap
     *
     * @param imageUrl bitmap的url
     * @return
     * @throws IOException
     */
    public Bitmap getBitmapFromUrl(String imageUrl) throws IOException {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);//将is解析为我们需要的bitmap
            connection.disconnect();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
        return null;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mImageView.setImageBitmap((Bitmap) msg.obj);
//            对图片的url进行判断,然后再获得不骗,避免了缓存图片的影响
            if (mImageView.getTag().equals(imageUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
//
//    /**
//     * 添加一个ImageLoader的构造方法
//     * //    将一部分内存转化为我们的缓存空间
//     *
//     * @param listView
//     */
//    public ImageLoader(ListView listView) {
//        mListView = listView;//初始化
//        mTask = new HashSet<>();//初始化
//
////        首先获取最大可用内存,取最大可用内存的1/4作为缓存空间
//        int maxMemory = (int) Runtime.getRuntime().maxMemory();
//        int cacheSize = maxMemory / 4;
//        mCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap value) {
////                每次存入缓存时调用,返回图片的大小
//                return value.getByteCount();
//            }
//        };
//
//    }
//
//    /**
//     * 增加图片到缓存
//     *
//     * @param url
//     * @param bitmap
//     */
//    public void addBitmapToCache(String url, Bitmap bitmap) {
//
////            校验当前缓存是否存在,若不存在,则加到缓存中
//        if (getBitmapFromCache(url) == null) {
//            mCache.put(url, bitmap);
//        }
//
//    }
//
//    /**
//     * 从缓存中获取图片
//     *
//     * @param url
//     * @return
//     */
//    public Bitmap getBitmapFromCache(String url) {
//        return mCache.get(url);
//    }
//
//    /**
//     * 加载从start到end的所有图片
//     *
//     * @param start
//     * @param end
//     */
//    public void loadImages(int start, int end) {
//        for (int i = start; i < end; i++) {
//            String url = NewAdapter.URLS[i];
//            Bitmap bitmap = getBitmapFromCache(url);
////       如果缓存没有,则必须从网络下载
//            if (bitmap == null) {
//                NewsAsncTask task = new NewsAsncTask(url);
//                task.execute(url);
//                mTask.add(task);
//            } else {
////            如果有,直接使用已有的图片
//                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }
//
//    /**
//     * 取消所有正在执行下载任务的方法
//     */
//    public void cancelAllTask(){
//        if (mTask!=null){
//            for (NewsAsncTask task:mTask){
//                task.cancel(false);
//            }
//        }
//    }
//
//    /**
//     * 获取图片的第二种方法,通过AsncTask.
//     *
//     * @param imageView 图片
//     * @param url       对应的url
//     */
//    public void showImageByAsncTask(ImageView imageView, String url) {
//////        调用AsncTask方法得到图片
////        new NewsAsncTask(imageView, url).execute(url);
//
////        从缓存中取出对应的图片
//        Bitmap bitmap = getBitmapFromCache(url);
////       如果缓存没有,则必须从网络下载
//        if (bitmap == null) {
//            imageView.setImageResource(R.mipmap.ic_launcher);
//        } else {
////            如果有,直接使用已有的图片
//            imageView.setImageBitmap(bitmap);
//        }
//    }
//
//    /**
//     * 第一个参数,url为String类型
//     * 第二个参数不需要  Void
//     * 第三个参数,返回为Bitmap
//     * 异步加载也存在缓存的问题
//     */
//    private class NewsAsncTask extends AsyncTask<String, Void, Bitmap> {
//
//        //        private ImageView mImageView;
//        private String mUrl;
//
//        public NewsAsncTask(String url) {
////            mImageView = imageView;
//            mUrl = url;
//        }
//
//        /**
//         * 完成异步加载的方法
//         * 将下载完成的图片保存到Cache
//         *
//         * @param params
//         * @return
//         */
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            String url = params[0];
////            从网络获取图片
//            try {
//                Bitmap bitmap = getBitmapFromUrl(url);
//                if (bitmap != null) {
////                    将不在缓存中的图片加入缓存
//                    addBitmapToCache(url, bitmap);
//                }
//                return bitmap;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        //          将bitmap设置给ImageView
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
////            增加一个身份判断
////            if (mImageView.getTag().equals(mUrl)) {
////                mImageView.setImageBitmap(bitmap);
////            }
//
////            通过Tag去寻找
//            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
//            if (imageView != null && bitmap != null) {
//                imageView.setImageBitmap(bitmap);
//            }
//            mTask.remove(this);
//        }
//
//    }
}