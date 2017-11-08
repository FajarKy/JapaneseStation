package com.hafiizh.japanesestation.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.databinding.ActivitySplashBinding;
import com.hafiizh.japanesestation.ui.activity.login.Login;

public class Splash extends BaseCompatActivity<ActivitySplashBinding> {
    public static final String TAG = Splash.class.getSimpleName();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.trackScreenView(TAG);
        Common.splash(this, Login.class);
    }
}
