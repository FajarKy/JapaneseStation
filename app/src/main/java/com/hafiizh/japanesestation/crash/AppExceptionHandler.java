package com.hafiizh.japanesestation.crash;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.hafiizh.japanesestation.App;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.common.SharedPref;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Activity activity;

    public AppExceptionHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        /**
         * @crash app has been crashed
         * @restart restart app to run system
         */
        Common.trackException(e);
        Common.trackScreenView(activity.getClass().getSimpleName() + " has been crashed");
        Common.trackEvent("Exception", "restart app", "" + e.getMessage());

        /**
         * @clear clear all activity to preparing restart app
         */
        Intent intent = new Intent(activity, CrashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                App.getInstance(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        /**
         * @scheduler set time to relaunch app
         */
        AlarmManager alarmManager = (AlarmManager) App.getInstance().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null)
            alarmManager.set(AlarmManager.RTC, 1000, pendingIntent);

        SharedPref.putBoolean("restart", true);

        /**
         * @close closing activity
         */
        activity.finish();

        System.exit(0);
    }
}
