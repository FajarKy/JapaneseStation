package com.hafiizh.japanesestation.ui.activity.detail;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.databinding.ActivityDetailBinding;

import javax.inject.Inject;

public class Detail extends BaseCompatActivity<ActivityDetailBinding> {
    public static final String TAG = Detail.class.getSimpleName();
    AdRequest adRequest;
    String url;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    DetailViewModel viewModel;

    ProgressDialog dialog;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    public Toolbar getToolbar() {
        return dataBinding.toolbar;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        url = getIntent().getStringExtra("launch_url");
        Common.trackScreenView(url);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait... \nSedang memuat ulang...");
        dialog.show();
        dataBinding.fab.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT, url);
            share.setType("text/plain");
            startActivity(Intent.createChooser(share, "Share article to..."));
        });
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
        viewModel.setUrl(url);
        viewModel.getDetail().observe(this, detailEntityResource -> {
            if (detailEntityResource != null) {
                dataBinding.setDetail(detailEntityResource.data);
                dialog.dismiss();
            } else {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}