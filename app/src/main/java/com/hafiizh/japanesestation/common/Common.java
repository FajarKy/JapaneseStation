package com.hafiizh.japanesestation.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.hafiizh.japanesestation.App;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.net.NetworkChangeReceiver;
import com.hafiizh.japanesestation.utilities.Constants;
import com.hafiizh.japanesestation.view.NetworkChangeInterface;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class Common {
    public Common() {
    }

    public static String sub(String s) {
        return "\t\t\t\t\t" + s;
    }

    public static void appCrash(boolean value) {
        if (value)
            showLongToast("App has been restarted ...");
    }

    public static void setupRefresh(SwipeRefreshLayout refresh) {
        refresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    public static void setupRecyclerView(RecyclerView rv, LinearLayoutManager linearLayoutManager) {
        rv.setHasFixedSize(true);
        rv.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(linearLayoutManager);
    }

    public static void setupAds(AdView adView, AdRequest adRequest) {
        MobileAds.initialize(App.getContext(), Constants.ADS.APP_ID);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    /**
     * @param c class destination
     */
    public static void splash(Activity activity, final Class c) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            App.getContext().startActivity(new Intent(App.getContext(), c));
            activity.finish();
        }, 3000);
    }

    /**
     * @param c destination class
     */
    public static void newActivity(final Class c) {
        App.getContext().startActivity(new Intent(App.getContext(), c).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * @param key   for shared preference key
     * @param value value of the key shared preference
     */
    public static void putStringSP(String[] key, String[] value) {
        for (int i = 0; i < value.length; i++) {
            SharedPref.putString(key[i], value[i]);
        }
    }

    public static void putIntSP(String[] key, int[] value) {
        for (int i = 0; i < value.length; i++) {
            SharedPref.putInt(key[i], value[i]);
        }
    }

    public static void putBooleanSP(String[] key, boolean[] value) {
        for (int i = 0; i < value.length; i++) {
            SharedPref.putBoolean(key[i], value[i]);
        }
    }

    public static void putLongSP(String[] key, long[] value) {
        for (int i = 0; i < value.length; i++) {
            SharedPref.putLong(key[i], value[i]);
        }
    }

    public static void newActivity(final Class a, String key, String value) {
        Intent intent = new Intent(App.getContext(), a);
        intent.putExtra(key, value);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

    public static void newActivity(final Class a, String key, int value) {
        Intent intent = new Intent(App.getContext(), a);
        intent.putExtra(key, value);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

    public static void newActivity(final Class a, String[] key, String[] value) {
        Intent intent = new Intent(App.getContext(), a);
        for (int i = 0; i < value.length; i++) {
            intent.putExtra(key[i], value[i]);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }

    public static void showToast(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * @param context for getContext activity for build alert
     * @param message for message the alert
     */
    public static void showDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Japanese Station")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(message)
                .setCancelable(false)
                .setNeutralButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                });
        AlertDialog alert = builder.create();

        if (!alert.isShowing())
            alert.show();
    }

    public static void registerBroadcast(Context context, NetworkChangeInterface changeInterface) {
        context.registerReceiver(new NetworkChangeReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra("net", 0) == Constants.NETWORK.TYPE_WIFI) {
                    changeInterface.wifiIsON();
                } else if (intent.getIntExtra("net", 0) == Constants.NETWORK.TYPE_MOBILE) {
                    changeInterface.mobileIsON();
                } else if (intent.getIntExtra("net", 0) == Constants.NETWORK.TYPE_NOT_CONNECTED) {
                    changeInterface.notConnected();
                }
            }
        }, new IntentFilter(NetworkChangeReceiver.NOTIFY_NETWORK_CHANGE));
    }

    public static void unregisterBroadcast(Context context) {
        NetworkChangeReceiver receiver = new NetworkChangeReceiver();
        context.unregisterReceiver(receiver);
    }

    public static void showSnackbarOffline(View v) {
        Snackbar snackbar = Snackbar.make(v, "Anda sedang offline !", Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.android.settings",
                        "com.android.settings.Settings$DataUsageSummaryActivity"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                App.getContext().startActivity(intent);
            }
        });
        TextView message = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        message.setTextColor(Color.YELLOW);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    public static void slidingCollapse(SlidingUpPanelLayout sliding) {
        sliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    public static void slidingHidden(SlidingUpPanelLayout sliding) {
        sliding.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
    }

    public static void adViewListener(AdView adView) {
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                showToast("onAdClicked");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                showToast("onAdClosed");
            }
        });
    }

    public static void closeAds(ImageView close, final AdView adView, final SlidingUpPanelLayout sliding) {
        close.setOnClickListener(v -> {
            adView.pause();
            slidingHidden(sliding);
        });
    }

    public static void trackEvent(String category, String action, String label) {
        App.getInstance().trackEvent(category, action, label);
    }

    public static void trackException(Throwable e) {
        App.getInstance().trackException(e);
    }

    public static void trackScreenView(String screen) {
        App.getInstance().trackScreenView(screen);
    }

    public static void setUserFacebookInfo(JSONObject object, Profile profile, LoginResult loginResult) {
        SharedPref.putInt(Constants.AUTH.LOGIN, Constants.AUTH.FB_ACCOUNT);
        String[] keys_fb = {
                Constants.AUTH.FB_APP_ID,
                Constants.AUTH.FB_TOKEN,
                Constants.AUTH.FB_USER_ID,
                Constants.AUTH.FB_GRAPH.NAME,
                Constants.AUTH.FB_GRAPH.FIRST_NAME,
                Constants.AUTH.FB_GRAPH.LAST_NAME,
                Constants.AUTH.FB_GRAPH.ID,
                Constants.AUTH.FB_GRAPH.MIDDLE_NAME,
                Constants.AUTH.FB_GRAPH.IMAGE
        };
        String[] values_fb = {
                loginResult.getAccessToken().getApplicationId(),
                loginResult.getAccessToken().getToken(),
                loginResult.getAccessToken().getUserId(),
                profile.getName(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getId(),
                profile.getMiddleName(),
                profile.getProfilePictureUri(100, 100).toString()
        };
        putStringSP(keys_fb, values_fb);
        try {
//            if (object.has("id"))
//                SharedPref.putString(Constants.AUTH.FB_GRAPH.IMAGE, new URL("https://graph.facebook.com/" + object.getString("id") + "/picture?width=200&height=200").toString());
            if (object.has("email"))
                SharedPref.putString(Constants.AUTH.FB_GRAPH.EMAIL, object.getString("email"));
            if (object.has("gender"))
                SharedPref.putString(Constants.AUTH.FB_GRAPH.GENDER, object.getString("gender"));
        } catch (JSONException e) {
            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
        }
    }

    public static void setUserTwitterInfo(User user, TwitterSession twitterSession) {
        SharedPref.putLong(Constants.AUTH.TW_USER_ID, twitterSession.getUserId());
        SharedPref.putLong(Constants.AUTH.TW_GRAPH.id, user.id);

        String[] keysInt = {
                Constants.AUTH.LOGIN,
                Constants.AUTH.TW_GRAPH.favouritesCount,
                Constants.AUTH.TW_GRAPH.followersCount,
                Constants.AUTH.TW_GRAPH.friendsCount,
                Constants.AUTH.TW_GRAPH.listedCount,
                Constants.AUTH.TW_GRAPH.statusesCount,
                Constants.AUTH.TW_GRAPH.utcOffset
        };
        int[] valuesInt = {
                Constants.AUTH.TW_ACCOUNT,
                user.favouritesCount,
                user.followersCount,
                user.friendsCount,
                user.listedCount,
                user.statusesCount,
                user.utcOffset
        };
        putIntSP(keysInt, valuesInt);

        String[] keysString = {
                Constants.AUTH.TW_USER_NAME,
                Constants.AUTH.TW_AUTH_TOKEN,
                Constants.AUTH.TW_AUTH_TOKEN_SECRET,
                Constants.AUTH.TW_GRAPH.createdAt,
                Constants.AUTH.TW_GRAPH.description,
                Constants.AUTH.TW_GRAPH.emailAddress,
                Constants.AUTH.TW_GRAPH.isStr,
                Constants.AUTH.TW_GRAPH.lang,
                Constants.AUTH.TW_GRAPH.location,
                Constants.AUTH.TW_GRAPH.name,
                Constants.AUTH.TW_GRAPH.profileImageUrl,
                Constants.AUTH.TW_GRAPH.profileImageUrlHttps,
                Constants.AUTH.TW_GRAPH.profileBackgroundColor,
                Constants.AUTH.TW_GRAPH.profileBackgroundImageUrl,
                Constants.AUTH.TW_GRAPH.profileBackgroundImageUrlHttps,
                Constants.AUTH.TW_GRAPH.profileLinkColor,
                Constants.AUTH.TW_GRAPH.profileSidebarBorderColor,
                Constants.AUTH.TW_GRAPH.profileSidebarFillColor,
                Constants.AUTH.TW_GRAPH.profileTextColor,
                Constants.AUTH.TW_GRAPH.screenName,
                Constants.AUTH.TW_GRAPH.timeZone,
                Constants.AUTH.TW_GRAPH.url,
                Constants.AUTH.TW_GRAPH.withheldScope
        };
        String[] valuesString = {
                twitterSession.getUserName(),
                twitterSession.getAuthToken().token,
                twitterSession.getAuthToken().secret,
                user.createdAt,
                user.description,
                user.email,
                user.idStr,
                user.lang,
                user.location,
                user.name,
                user.profileImageUrl,
                user.profileImageUrlHttps,
                user.profileBackgroundColor,
                user.profileBackgroundImageUrl,
                user.profileBackgroundImageUrlHttps,
                user.profileLinkColor,
                user.profileSidebarBorderColor,
                user.profileSidebarFillColor,
                user.profileTextColor,
                user.screenName,
                user.timeZone,
                user.url,
                user.withheldScope
        };
        putStringSP(keysString, valuesString);

        String[] keysBool = {
                Constants.AUTH.TW_GRAPH.contributorsEnabled,
                Constants.AUTH.TW_GRAPH.defaultProfile,
                Constants.AUTH.TW_GRAPH.defaultProfileImage,
                Constants.AUTH.TW_GRAPH.followRequestSent,
                Constants.AUTH.TW_GRAPH.geoEnabled,
                Constants.AUTH.TW_GRAPH.isTranslator,
                Constants.AUTH.TW_GRAPH.profileBackgroundTile,
                Constants.AUTH.TW_GRAPH.profileUseBackgroundImage,
                Constants.AUTH.TW_GRAPH.protectedUser,
                Constants.AUTH.TW_GRAPH.showAllInlineMedia,
                Constants.AUTH.TW_GRAPH.verified
        };
        boolean[] valuesBool = {
                user.contributorsEnabled,
                user.defaultProfile,
                user.defaultProfileImage,
                user.followRequestSent,
                user.geoEnabled,
                user.isTranslator,
                user.profileBackgroundTile,
                user.profileUseBackgroundImage,
                user.protectedUser,
                user.showAllInlineMedia,
                user.verified
        };
        putBooleanSP(keysBool, valuesBool);
    }

    public static void progDil(boolean condition) {
        ProgressDialog dialog = new ProgressDialog(App.getContext());
        dialog.setMessage("Please wait...");
        dialog.setCancelable(condition);
        dialog.show();
    }
}