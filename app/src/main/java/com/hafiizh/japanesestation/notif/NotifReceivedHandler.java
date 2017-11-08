package com.hafiizh.japanesestation.notif;

import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class NotifReceivedHandler implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String customKey;
        if (data != null) {
            customKey = data.optString("customKey", null);
            if (customKey != null) {
                Log.i("ReceiveHandler", "customkey set with value : " + customKey);
            }
        }
    }
}