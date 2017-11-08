package com.hafiizh.japanesestation.ui.activity.bookmark;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.databinding.ActivityBookmarkBinding;
import com.hafiizh.japanesestation.ui.activity.detail.Detail;

import javax.inject.Inject;

public class Bookmark extends BaseCompatActivity<ActivityBookmarkBinding> {
    public static final String TAG = Bookmark.class.getSimpleName();
    BookmarkAdapter adapter;
    AdRequest adRequest;
    LinearLayoutManager layoutManager;

    @Inject
    ViewModelProvider.Factory factory;

    BookmarkListViewModel getBookmark;
    SaveandDelBookmarkViewModel save_and_del;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_bookmark;
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
        Common.setupAds(dataBinding.adsBookmark, adRequest);
        Common.setupRefresh(dataBinding.refreshBookmark);
        layoutManager = new LinearLayoutManager(this);
        Common.setupRecyclerView(dataBinding.recylerBookmark, layoutManager);
        Common.closeAds(dataBinding.closeAds, dataBinding.adsBookmark, dataBinding.slidingBookmark);
        Common.adViewListener(dataBinding.adsBookmark);
        dataBinding.slidingBookmark.setTouchEnabled(false);
        adapter = new BookmarkAdapter();
        dataBinding.recylerBookmark.setAdapter(adapter);
        save_and_del = ViewModelProviders.of(this, factory).get(SaveandDelBookmarkViewModel.class);
        adapter.setOnSourceItemClickListener((v, position, response) -> {
            Common.trackEvent("List " + TAG, "click", response.get(position).getTitle_link());
            String[] key = {"title", "launch_url"};
            String[] value = {response.get(position).getTitle(), response.get(position).getTitle_link()};
            Common.newActivity(Detail.class, key, value);
        });
        adapter.setEntityOnSourceItemClickListener((v, position, response) -> {
            Common.showToast("Bookmark");
            save_and_del.deleteBookmark(response.get(position));
        });
        getBookmark = ViewModelProviders.of(this, factory).get(BookmarkListViewModel.class);
        getBookmark.getBookmark().observe(this, bookmarkEntities -> {
            if (bookmarkEntities == null) {
                adapter.replace(null);
            } else {
                adapter.replace(bookmarkEntities);
            }
        });
    }

    @Override
    public void onPause() {
        dataBinding.adsBookmark.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.adsBookmark.resume();
        Common.trackScreenView(TAG);
        Common.slidingCollapse(dataBinding.slidingBookmark);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}