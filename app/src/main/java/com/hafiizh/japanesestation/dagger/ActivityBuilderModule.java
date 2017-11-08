package com.hafiizh.japanesestation.dagger;

import com.hafiizh.japanesestation.crash.CrashActivity;
import com.hafiizh.japanesestation.ui.activity.About;
import com.hafiizh.japanesestation.ui.activity.Disclaimer;
import com.hafiizh.japanesestation.ui.activity.PrivacyPolicy;
import com.hafiizh.japanesestation.ui.activity.TermsOfUse;
import com.hafiizh.japanesestation.ui.activity.bookmark.Bookmark;
import com.hafiizh.japanesestation.ui.activity.detail.Detail;
import com.hafiizh.japanesestation.ui.activity.kategori.Kategori;
import com.hafiizh.japanesestation.ui.activity.MainActivity;
import com.hafiizh.japanesestation.ui.activity.Splash;
import com.hafiizh.japanesestation.ui.activity.login.Login;
import com.hafiizh.japanesestation.ui.activity.settings.SettingsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract Splash splash();

    @ContributesAndroidInjector
    abstract Kategori kategori();

    @ContributesAndroidInjector
    abstract CrashActivity crashActivity();

    @ContributesAndroidInjector
    abstract Detail detail();

    @ContributesAndroidInjector
    abstract About about();

    @ContributesAndroidInjector
    abstract Login login();

    @ContributesAndroidInjector
    abstract Bookmark bookmark();

    @ContributesAndroidInjector
    abstract SettingsActivity settings();

    @ContributesAndroidInjector
    abstract Disclaimer disclaimer();

    @ContributesAndroidInjector
    abstract PrivacyPolicy privacyPolicy();

    @ContributesAndroidInjector
    abstract TermsOfUse termsOfUse();
}