package com.hafiizh.japanesestation.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.databinding.ActivityDisclaimerBinding;

public class Disclaimer extends BaseCompatActivity<ActivityDisclaimerBinding> {
    public static final String TAG = Disclaimer.class.getSimpleName();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_disclaimer;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Common.trackScreenView(TAG);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}