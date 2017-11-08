package com.hafiizh.japanesestation.base;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.crash.AppExceptionHandler;
import com.hafiizh.japanesestation.view.NetworkChangeInterface;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector, NetworkChangeInterface {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    public DB dataBinding;

    @LayoutRes
    public abstract int getLayoutRes();

    @SuppressLint("SupportAnnotationUsage")
    @IdRes
    public abstract Toolbar getToolbar();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        Thread.setDefaultUncaughtExceptionHandler(new AppExceptionHandler(this));

        if (getToolbar() != null)
            setSupportActionBar(getToolbar());

        if (SharedPref.getBoolean("restart")) {
            Common.appCrash(getIntent().getBooleanExtra("crash", false));
            SharedPref.putBoolean("restart", false);
        }

        Common.registerBroadcast(this, this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void wifiIsON() {

    }

    @Override
    public void mobileIsON() {

    }

    @Override
    public void notConnected() {
        Common.showDialog(this, "Your device is not connected to internet !");
    }
}
