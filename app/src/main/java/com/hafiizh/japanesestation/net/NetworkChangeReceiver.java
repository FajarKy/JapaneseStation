package com.hafiizh.japanesestation.net;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.hafiizh.japanesestation.utilities.Constants;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    public static final String NOTIFY_NETWORK_CHANGE = "NOTIFY_NETWORK_CHANGE";

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent localIntent = new Intent(NOTIFY_NETWORK_CHANGE);
        if (NetworkUtil.getConnectivityStatus() == Constants.NETWORK.TYPE_WIFI)
            localIntent.putExtra("net", Constants.NETWORK.TYPE_WIFI);
        else if (NetworkUtil.getConnectivityStatus() == Constants.NETWORK.TYPE_MOBILE)
            localIntent.putExtra("net", Constants.NETWORK.TYPE_MOBILE);
        else if (NetworkUtil.getConnectivityStatus() == Constants.NETWORK.TYPE_NOT_CONNECTED)
            localIntent.putExtra("net", Constants.NETWORK.TYPE_NOT_CONNECTED);
        LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent);
    }
}