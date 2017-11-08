package com.hafiizh.japanesestation.base;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.crash.AppExceptionHandler;
import com.hafiizh.japanesestation.view.NetworkChangeInterface;

import dagger.android.AndroidInjection;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public abstract class BaseCompatActivity<DB extends ViewDataBinding> extends AppCompatActivity implements LifecycleRegistryOwner, NetworkChangeInterface {
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

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

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
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