package com.hafiizh.japanesestation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import com.hafiizh.japanesestation.common.AnalyticsTracker;
import com.hafiizh.japanesestation.dagger.DaggerAppComponent;
import com.hafiizh.japanesestation.notif.NotifOpenedHandler;
import com.hafiizh.japanesestation.notif.NotifReceivedHandler;
import com.onesignal.OneSignal;
import com.twitter.sdk.android.core.Twitter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class App extends Application implements HasActivityInjector {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @SuppressLint("StaticFieldLeak")
    private static App instance;
    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;

        DaggerAppComponent.builder().application(this).build().inject(this);
        AnalyticsTracker.initialize(this);
        AnalyticsTracker.getInstance().get(AnalyticsTracker.Target.APP);
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new NotifOpenedHandler())
                .setNotificationReceivedHandler(new NotifReceivedHandler())
                .init();
        Twitter.initialize(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public static synchronized Context getContext() {
        return context;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTracker analyticsTracker = AnalyticsTracker.getInstance();
        return analyticsTracker.get(AnalyticsTracker.Target.APP);
    }

    /**
     * Tracking SCREEN VIEW
     *
     * @param screenName screen name to be displayed on Google Analytics Dashboard
     */
    public void trackScreenView(String screenName) {
        Tracker tracker = getGoogleAnalyticsTracker();
        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    /**
     * Tracking THROWABLE
     *
     * @param e exception to be tracked
     */
    public void trackException(Throwable e) {
        if (!e.equals(null)) {
            Tracker tracker = getGoogleAnalyticsTracker();
            tracker.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(new StandardExceptionParser(this, null)
                            .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build());
        }
    }

    /**
     * Tracking EVENT
     *
     * @param category -> event category
     * @param action   -> action of the event
     * @param label    -> label
     */
    public void trackEvent(String category, String action, String label) {
        Tracker tracker = getGoogleAnalyticsTracker();
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }
}