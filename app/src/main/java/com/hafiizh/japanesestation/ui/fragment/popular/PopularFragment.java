package com.hafiizh.japanesestation.ui.fragment.popular;

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
import com.hafiizh.japanesestation.data.local.entity.PopularEntity;
import com.hafiizh.japanesestation.databinding.FragmentPopularBinding;
import com.hafiizh.japanesestation.ui.activity.bookmark.SaveandDelBookmarkViewModel;
import com.hafiizh.japanesestation.ui.activity.detail.Detail;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class PopularFragment extends BaseFragment<PopularListViewModel, FragmentPopularBinding> {
    public static final String TAG = PopularFragment.class.getSimpleName();
    PopularAdapter adapter;
    AdRequest adRequest;
    LinearLayoutManager layoutManager;

    @SuppressLint("StaticFieldLeak")
    public static RecyclerView recyclerView;

    BookmarkEntity bookmarkEntity;
    SaveandDelBookmarkViewModel save_and_del;

    int page = 1;

    @Override
    public Class<PopularListViewModel> getViewModel() {
        return PopularListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_popular;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = dataBinding.recylerPopular;
        Common.trackScreenView(TAG);
        Common.setupAds(dataBinding.adsPopular, adRequest);
        Common.setupRefresh(dataBinding.refreshPopular);
        layoutManager = new LinearLayoutManager(getActivity());
        Common.setupRecyclerView(dataBinding.recylerPopular, layoutManager);
        Common.closeAds(dataBinding.closeAds, dataBinding.adsPopular, dataBinding.slidingPopular);
        Common.adViewListener(dataBinding.adsPopular);
        dataBinding.slidingPopular.setTouchEnabled(false);
        adapter = new PopularAdapter();
        dataBinding.recylerPopular.setAdapter(adapter);
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
            PopularEntity entity = response.get(position);
            bookmarkEntity = new BookmarkEntity();
            bookmarkEntity.setImage(entity.getImage());
            bookmarkEntity.setTag(entity.getTag());
            bookmarkEntity.setTag_link(entity.getTag_link());
            bookmarkEntity.setTitle(entity.getTitle());
            bookmarkEntity.setTitle_link(entity.getTitle_link());
            bookmarkEntity.setSub_title(entity.getSub_title());
            bookmarkEntity.setAuthor(entity.getAuthor());
            bookmarkEntity.setAuthor_link(entity.getAuthor_link());
            bookmarkEntity.setImage_author(entity.getImage_author());
            bookmarkEntity.setTime_publish(entity.getTime_publish());
            bookmarkEntity.setDay_publish(entity.getDay_publish());
            bookmarkEntity.setTime_update(entity.getTime_update());
            bookmarkEntity.setDay_update(entity.getDay_update());
            save_and_del.saveBookmark(bookmarkEntity);
        });
        dataBinding.recylerPopular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("VisibleForTests")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                    page = page + 1;
                    viewModel.setPage(page);
                    viewModel.getPopular().observe(getActivity(), listResource -> {
                        dataBinding.refreshPopular.setRefreshing(true);
                        if (listResource == null)
                            adapter.replace(null, dataBinding.refreshPopular);
                        else
                            adapter.replace(listResource.data, dataBinding.refreshPopular);
                    });
                }
            }
        });
        dataBinding.refreshPopular.setOnRefreshListener(() -> {
            dataBinding.refreshPopular.setRefreshing(true);
            viewModel.setPage(page);
            viewModel.getPopular().observe(this, listResource -> {
                if (listResource == null)
                    adapter.replace(null, dataBinding.refreshPopular);
                else
                    adapter.replace(listResource.data, dataBinding.refreshPopular);
            });
        });
        return dataBinding.getRoot();
    }

    @SuppressLint("VisibleForTests")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.setPage(page);
        viewModel.getPopular().observe(this, listResource -> {
            dataBinding.refreshPopular.setRefreshing(true);
            if (listResource == null)
                adapter.replace(null, dataBinding.refreshPopular);
            else
                adapter.replace(listResource.data, dataBinding.refreshPopular);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.adsPopular.resume();
        Common.trackScreenView(TAG);
        Common.slidingCollapse(dataBinding.slidingPopular);
    }

    @Override
    public void onPause() {
        dataBinding.adsPopular.pause();
        super.onPause();
    }
}