package com.hafiizh.japanesestation.utilities;

import com.hafiizh.japanesestation.R;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class Constants {
    public static final String TRENDING = "TRENDING";
    public static final String HOT = "HOT";
    public static final String POPULAR = "POPULAR";
    public static final String LATEST = "LATEST";

    public static final class AUTH {
        public static final String NOTIF = "notif";
        public static final String LATEST_OPEN = "latest_open";
        public static final String HOT_OPEN = "hot_open";
        public static final String POPULAR_OPEN = "popular_open";
        public static final String TRENDING_OPEN = "trending_open";
        public static final String KATEGORI_OPEN = "kategori_open";
        public static final String LOGIN = "login";
        public static final int FB_ACCOUNT = 11;
        public static final int TW_ACCOUNT = 12;
        public static final String FB_APP_ID = "fb_app_id";
        public static final String FB_TOKEN = "fb_token";
        public static final String FB_USER_ID = "fb_user_id";
        public static final String TW_AUTH_TOKEN = "tw_auth_token";
        public static final String TW_AUTH_TOKEN_SECRET = "tw_auth_token_secret";
        public static final String TW_USER_ID = "tw_user_id";
        public static final String TW_USER_NAME = "tw_user_name";

        public static final class FB_GRAPH {
            public static final String ID = "id";
            public static final String BIRTHDAY = "birthday";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String MIDDLE_NAME = "middle_name";
            public static final String LINK_URI = "link_uri";
            public static final String GENDER = "gender";
            public static final String LINK = "link";
            public static final String LOCALE = "locale";
            public static final String NAME = "name";
            public static final String TIMEZONE = "timezone";
            public static final String UPDATED_TIME = "updated_time";
            public static final String VERIFIED = "verified";
            public static final String EMAIL = "email";
            public static final String IMAGE = "image";
        }

        public static final class TW_GRAPH {
            public static final String contributorsEnabled = "contributorsEnabled";
            public static final String createdAt = "createdAt";
            public static final String defaultProfile = "defaultProfile";
            public static final String defaultProfileImage = "defaultProfileImage";
            public static final String description = "description";
            public static final String emailAddress = "emailAddress";
            public static final String entities = "entities";
            public static final String favouritesCount = "favouritesCount";
            public static final String followRequestSent = "followRequestSent";
            public static final String followersCount = "followersCount";
            public static final String friendsCount = "friendsCount";
            public static final String geoEnabled = "geoEnabled";
            public static final String id = "id";
            public static final String isStr = "idStr";
            public static final String isTranslator = "isTranslator";
            public static final String lang = "lang";
            public static final String listedCount = "listedCount";
            public static final String location = "location";
            public static final String name = "name";
            public static final String profileBackgroundColor = "profileBackgroundColor";
            public static final String profileBackgroundImageUrl = "profileBackgroundImageUrl";
            public static final String profileBackgroundImageUrlHttps = "profileBackgroundImageUrlHttps";
            public static final String profileBackgroundTile = "profileBackgroundTile";
            public static final String profileBannerUrl = "profileBannerUrl";
            public static final String profileImageUrl = "profileImageUrl";
            public static final String profileImageUrlHttps = "profileImageUrlHttps";
            public static final String profileLinkColor = "profileLinkColor";
            public static final String profileSidebarBorderColor = "profileSidebarBorderColor";
            public static final String profileSidebarFillColor = "profileSidebarFillColor";
            public static final String profileTextColor = "profileTextColor";
            public static final String profileUseBackgroundImage = "profileUseBackgroundImage";
            public static final String protectedUser = "protectedUser";
            public static final String screenName = "screenName";
            public static final String showAllInlineMedia = "showAllInlineMedia";
            public static final String status = "status";
            public static final String statusesCount = "statusesCount";
            public static final String timeZone = "timeZone";
            public static final String url = "url";
            public static final String utcOffset = "utcOffset";
            public static final String verified = "verified";
            public static final String withheldInCountries = "withheldInCountries";
            public static final String withheldScope = "withheldScope";
        }
    }

    public static final class ADS {
        public static final String APP_ID = "ca-app-pub-8929266657072147~4702748413";
        public static final String BANNER = "ca-app-pub-8929266657072147/4726799144";
    }

    public static final class API {
        public static final String BASE_API = "http://fiizbghul.com/js/";
        public static final String BASE_API_V2 = "http://fiizhghul.000webhostapp.com/js/";
        public static final String POPULAR = "popular.php?";
        public static final String HOT = "hot.php?";
        public static final String TRENDING = "trending.php?";
        public static final String POST = "post.php?";
        public static final String DETAIL = "detail_post.php?";
        public static final String KATEGORI = "kategori.php?";
        public static final String SEARCH = "search.php?";
    }

    public static final class NETWORK {
        public static final int TYPE_WIFI = 1;
        public static final int TYPE_MOBILE = 2;
        public static final int TYPE_NOT_CONNECTED = 0;
    }

    public static final class HTTP {
        public static final String BASE_URL = "https://japanesestation.com/wp-json/wp/v2/";
        public static final String POST = "posts";
        public static final String CATEGORY = "categories";
        public static final String TAGS = "tags";
        public static final String USERS = "users";
        public static final String PAGES = "pages";
    }

    public static class PAGE {
        public static int HOT = 1;
        public static int LATEST = 1;
        public static int POPULAR = 1;
        public static int TRENDING = 1;
        public static int KATEGORI = 1;
    }

    public static final class QUERY {
        public static final String URL = "url";
        public static final String[] KEYS = {"kategori"};
        public static final String[] VALUES = {"48world", "dorama", "movie", "music", "indie_scene", "stage_play",
                "tv_show", "sport", "anime", "cosplay", "games", "hobby", "light_novel", "manga", "tokusatsu",
                "vocaloid", "culinary", "fashion", "life", "news", "place_travel", "technology", "art", "festival",
                "figure", "history", "myth", "sites", "traditional", "concert", "event", "upcoming_events", "advertorial",
                "js_diary", "learn", "review"};
        public static String[] keys = {"notification_id", "title", "body", "small_icon", "large_icon", "big_picture", "small_icon_accent",
                "launch_url", "sound", "led_color", "group_key", "group_message", "from_project_number", "collapse_id",
                "priority", "raw_payload"};
    }

    public static final class TAB {
        public static final int[] tabIcon = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background};
    }

    public static void RESET_PAGE() {
        PAGE.HOT = 1;
        PAGE.LATEST = 1;
        PAGE.POPULAR = 1;
        PAGE.TRENDING = 1;
        PAGE.KATEGORI = 1;
    }
}