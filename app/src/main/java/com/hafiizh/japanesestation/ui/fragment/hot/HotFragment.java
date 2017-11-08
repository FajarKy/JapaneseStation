package com.hafiizh.japanesestation.ui.fragment.hot;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseFragment;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;
import com.hafiizh.japanesestation.data.local.entity.HotEntity;
import com.hafiizh.japanesestation.databinding.FragmentHotBinding;
import com.hafiizh.japanesestation.ui.activity.bookmark.SaveandDelBookmarkViewModel;
import com.hafiizh.japanesestation.ui.activity.detail.Detail;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class HotFragment extends BaseFragment<HotListViewModel, FragmentHotBinding> {
    public static final String TAG = HotFragment.class.getSimpleName();
    HotAdapter adapter;
    AdRequest adRequest;
    LinearLayoutManager layoutManager;

    @SuppressLint("StaticFieldLeak")
    public static RecyclerView recyclerView;

    BookmarkEntity bookmarkEntity;
    SaveandDelBookmarkViewModel save_and_del;

    int page = 1;

    @Override
    public Class<HotListViewModel> getViewModel() {
        return HotListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_hot;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = dataBinding.recylerHot;
        Common.trackScreenView(TAG);
        Common.setupAds(dataBinding.adsHot, adRequest);
        Common.setupRefresh(dataBinding.refreshHot);
        layoutManager = new LinearLayoutManager(getActivity());
        Common.setupRecyclerView(dataBinding.recylerHot, layoutManager);
        Common.closeAds(dataBinding.closeAds, dataBinding.adsHot, dataBinding.slidingHot);
        Common.adViewListener(dataBinding.adsHot);
        dataBinding.slidingHot.setTouchEnabled(false);
        adapter = new HotAdapter();
        dataBinding.recylerHot.setAdapter(adapter);
        save_and_del = ViewModelProviders.of(this, viewModelFactory).get(SaveandDelBookmarkViewModel.class);
        adapter.setOnSourceItemClickListener((v, position, response) -> {
            Toast.makeText(getActivity(), response.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            Common.trackEvent("List " + TAG, "click", response.get(position).getTitle_link());
            String[] key = {"title", "launch_url"};
            String[] value = {response.get(position).getTitle(), response.get(position).getTitle_link()};
            Common.newActivity(Detail.class, key, value);
        });
        adapter.setEntityOnSourceItemClickListener((v, position, response) -> {
            Common.showToast("Bookmark clicked");
            HotEntity hotEntity = response.get(position);
            bookmarkEntity = new BookmarkEntity();
            bookmarkEntity.setImage(hotEntity.getImage());
            bookmarkEntity.setTag(hotEntity.getTag());
            bookmarkEntity.setTag_link(hotEntity.getTag_link());
            bookmarkEntity.setTitle(hotEntity.getTitle());
            bookmarkEntity.setTitle_link(hotEntity.getTitle_link());
            bookmarkEntity.setSub_title(hotEntity.getSub_title());
            bookmarkEntity.setAuthor(hotEntity.getAuthor());
            bookmarkEntity.setAuthor_link(hotEntity.getAuthor_link());
            bookmarkEntity.setImage_author(hotEntity.getImage_author());
            bookmarkEntity.setTime_publish(hotEntity.getTime_publish());
            bookmarkEntity.setDay_publish(hotEntity.getDay_publish());
            bookmarkEntity.setTime_update(hotEntity.getTime_update());
            bookmarkEntity.setDay_update(hotEntity.getDay_update());
            save_and_del.saveBookmark(bookmarkEntity);
        });
        dataBinding.recylerHot.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                    dataBinding.refreshHot.setRefreshing(true);
                    page = page + 1;
                    viewModel.setPage(page);
                    viewModel.getHot().observe(getActivity(), listResource -> {
                        if (listResource == null)
                            adapter.replace(null, dataBinding.refreshHot);
                        else
                            adapter.replace(listResource.data, dataBinding.refreshHot);
                    });
                }
            }
        });
        dataBinding.refreshHot.setOnRefreshListener(() -> {
            dataBinding.refreshHot.setRefreshing(true);
            viewModel.setPage(page);
            viewModel.getHot().observe(this, listResource -> {
                if (listResource == null)
                    adapter.replace(null, dataBinding.refreshHot);
                else
                    adapter.replace(listResource.data, dataBinding.refreshHot);
            });
        });
        return dataBinding.getRoot();
    }

    @SuppressLint("VisibleForTests")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBinding.refreshHot.setRefreshing(true);
        viewModel.setPage(page);
        viewModel.getHot().observe(this, listResource -> {
            if (listResource == null)
                adapter.replace(null, dataBinding.refreshHot);
            else
                adapter.replace(listResource.data, dataBinding.refreshHot);
        });
    }

    @Override
    public void onPause() {
        dataBinding.adsHot.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.adsHot.resume();
        Common.trackScreenView(TAG);
        Common.slidingCollapse(dataBinding.slidingHot);
    }
}