package com.example.android.newlead.main;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Android on 2016/6/13.
 */
public class JudgeNetworkConnection {
    private static Handler handler;
    private static Context context;
    public JudgeNetworkConnection(Context c,Handler h) {
        handler=h;
        context=c;
    }

    /**
     * 判断网络连接是否可用
     *
     * @param
     * @return
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            Toast.makeText(context, "网络断开", Toast.LENGTH_SHORT).show();
        } else {
            cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] infos = cm.getAllNetworkInfo();
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                        sendmessage(handler);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Handler sendmessage(Handler h) {
        handler=new Handler();
        handler.sendEmptyMessage(0);
        return h;
    }


    /**
     * 判断GPS是否打开
     */
    public static boolean isGPSEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleproviders = lm.getProviders(true);
        return accessibleproviders != null && accessibleproviders.size() > 0;
    }

    /**
     * 判断wifi是否打开
     */
    public static boolean iswifiEnabled(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrtel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED | mgrtel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断3G网络
     */
    public static boolean is3G(Context context) {
        ConnectivityManager cms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cms.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断wifi还是3G网络
     */
    public static boolean iswifito3G(Context context) {
        ConnectivityManager cmsd = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cmsd.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
}
