package com.hafiizh.japanesestation.notif;

import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.ui.activity.detail.Detail;
import com.hafiizh.japanesestation.ui.activity.login.Login;
import com.hafiizh.japanesestation.utilities.Constants;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OSNotificationPayload;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class NotifOpenedHandler implements OneSignal.NotificationOpenedHandler {
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        OSNotificationPayload payload = result.notification.payload;
        JSONObject data = payload.additionalData;
        String notificationID = payload.notificationID;
        String title = payload.title;
        String body = payload.body;
        String smallIcon = payload.smallIcon;
        String largeIcon = payload.largeIcon;
        String bigPicture = payload.bigPicture;
        String smallIconAccentColor = payload.smallIconAccentColor;
        String launchURL = payload.launchURL;
        String sound = payload.sound;
        String ledColor = payload.ledColor;
        String groupKey = payload.groupKey;
        String groupMessage = payload.groupMessage;
        String fromProjectNumber = payload.fromProjectNumber;
        String collapseId = payload.collapseId;
        int priority = payload.priority;
        String rawPayload = payload.rawPayload;

        String[] values = {notificationID, title, body, smallIcon, largeIcon, bigPicture, smallIconAccentColor, launchURL,
                sound, ledColor, groupKey, groupMessage, fromProjectNumber, collapseId, String.valueOf(priority), rawPayload};
        SharedPref.putBoolean(Constants.AUTH.NOTIF, true);

        if (SharedPref.getString(Constants.AUTH.FB_TOKEN) != null)
            Common.newActivity(Detail.class, Constants.QUERY.keys, values);
        else if (SharedPref.getString(Constants.AUTH.TW_AUTH_TOKEN) != null)
            Common.newActivity(Detail.class, Constants.QUERY.keys, values);
        else
            Common.newActivity(Login.class);
    }
}