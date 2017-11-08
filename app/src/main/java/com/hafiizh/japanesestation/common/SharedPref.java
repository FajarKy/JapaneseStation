package com.hafiizh.japanesestation.common;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hafiizh.japanesestation.App;

/**
 * Created by HAFIIZH on 10/27/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class SharedPref {
    private static SharedPreferences getPref() {
        return PreferenceManager.getDefaultSharedPreferences(App.getContext());
    }

    public static void putBoolean(String key, boolean value) {
        getPref().edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        return getPref().getBoolean(key, false);
    }

    public static void putString(String key, String value) {
        getPref().edit().putString(key, value).apply();
    }

    public static void putAppOpen(String key, boolean value) {
        getPref().edit().putBoolean(key, value).apply();
    }

    public static boolean getAppOpen(String key) {
        return getPref().getBoolean(key, true);
    }

    public static String getString(String key) {
        return getPref().getString(key, null);
    }

    public static void putInt(String key, int value) {
        getPref().edit().putInt(key, value).apply();
    }

    public static int getInt(String key) {
        return getPref().getInt(key, 0);
    }

    public static void putLong(String key, long value) {
        getPref().edit().putLong(key, value).apply();
    }

    public static long getLong(String key) {
        return getPref().getLong(key, 0);
    }

    public static void delete(String key) {
        getPref().edit().remove(key).apply();
    }

    public static void clear() {
        getPref().edit().clear().apply();
    }

    public static boolean getNotif() {
        return getPref().getBoolean("notification", true);
    }
}