package com.hafiizh.japanesestation.common;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.hafiizh.japanesestation.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public final class AnalyticsTracker {
    private final Map<Target, Tracker> mTracker = new HashMap<>();
    private final Context context;

    public enum Target {
        APP
    }

    public AnalyticsTracker(Context context) {
        this.context = context;
    }

    private static AnalyticsTracker instance;

    public static synchronized void initialize(Context context) {
        if (instance != null)
            throw new IllegalStateException("Extra call to initialize analytics trackers");
        instance = new AnalyticsTracker(context);
    }

    public static synchronized AnalyticsTracker getInstance() {
        if (instance == null)
            throw new IllegalStateException("Call initialize() before getInstance()");
        return instance;
    }

    public synchronized Tracker get(Target target) {
        if (!mTracker.containsKey(target)) {
            Tracker tracker;
            switch (target) {
                case APP:
                    tracker = GoogleAnalytics.getInstance(context).newTracker(R.xml.global_tracker);
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled analytics target " + target);
            }
            mTracker.put(target, tracker);
        }
        return mTracker.get(target);
    }
}