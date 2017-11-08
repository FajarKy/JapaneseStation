package com.hafiizh.japanesestation.notif;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.hafiizh.japanesestation.App;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.common.SharedPref;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;

import java.math.BigInteger;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class NotifExtenderService extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        if (SharedPref.getNotif()) {
            OverrideSettings overrideSettings = new OverrideSettings();
            overrideSettings.extender = builder -> {
                Bitmap icon = BitmapFactory.decodeResource(App.getContext().getResources(), R.mipmap.ic_launcher);
                builder.setLargeIcon(icon);
                builder.setColor(new BigInteger("FF0000FF", 16).intValue());
                return builder;
            };
            OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
            Log.d("ExtenderService", "Notification displayed with id : " + displayedResult.androidNotificationId);
        }
        return true;
    }
}