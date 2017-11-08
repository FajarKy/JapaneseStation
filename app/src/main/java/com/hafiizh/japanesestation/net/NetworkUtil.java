package com.hafiizh.japanesestation.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hafiizh.japanesestation.App;
import com.hafiizh.japanesestation.utilities.Constants;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class NetworkUtil {
    public static int getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (cm != null)
            info = cm.getActiveNetworkInfo();
        if (info != null)
            if (info.getType() == ConnectivityManager.TYPE_WIFI)
                return Constants.NETWORK.TYPE_WIFI;
            else if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                return Constants.NETWORK.TYPE_MOBILE;
        return Constants.NETWORK.TYPE_NOT_CONNECTED;
    }

    public static String getMessageStatus() {
        int conn = getConnectivityStatus();
        String status = null;
        if (conn == Constants.NETWORK.TYPE_WIFI)
            status = "Wifi enabled";
        else if (conn == Constants.NETWORK.TYPE_MOBILE)
            status = "Mobile data enabled";
        else if (conn == Constants.NETWORK.TYPE_NOT_CONNECTED)
            status = "Not connected to Internet";
        return status;
    }
}