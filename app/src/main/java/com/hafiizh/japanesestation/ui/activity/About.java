package com.hafiizh.japanesestation.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.databinding.ActivityAboutBinding;

public class About extends BaseCompatActivity<ActivityAboutBinding> {
    public static final String TAG = About.class.getSimpleName();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_about;
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