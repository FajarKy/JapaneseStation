package com.hafiizh.japanesestation.ui.activity.kategori;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.base.BaseCompatActivity;
import com.hafiizh.japanesestation.common.Common;
import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;
import com.hafiizh.japanesestation.data.local.entity.KategoriEntity;
import com.hafiizh.japanesestation.data.local.entity.KategoriTagEntity;
import com.hafiizh.japanesestation.data.repository.kategori.KategoriTagPresenter;
import com.hafiizh.japanesestation.data.repository.kategori.KategoriTagPresenterImpl;
import com.hafiizh.japanesestation.data.repository.kategori.KategoriTagView;
import com.hafiizh.japanesestation.databinding.ActivityKategoriBinding;
import com.hafiizh.japanesestation.ui.activity.bookmark.SaveandDelBookmarkViewModel;
import com.hafiizh.japanesestation.ui.activity.detail.Detail;
import com.hafiizh.japanesestation.utilities.Constants;

import java.util.List;

import javax.inject.Inject;

public class Kategori extends BaseCompatActivity<ActivityKategoriBinding> implements KategoriTagView {
    public static final String TAG = Kategori.class.getSimpleName();
    KategoriAdapter adapter;
    KategoriTagAdapter tagAdapter;
    AdRequest adRequest;
    LinearLayoutManager layoutManager, tagLayoutManager;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    BookmarkEntity bookmarkEntity;
    SaveandDelBookmarkViewModel save_and_del;

    KategoriTagPresenter presenter;

    KategoriListViewModel viewModel;

    ProgressDialog dialog;

    public static String kate;
    int page = 1;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_kategori;
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
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Please wait... \nSedang memuat ulang...");
        dialog.show();

        kate = getIntent().getStringExtra("kategori");

        Common.trackScreenView(TAG + " - " + kate);
        Common.setupAds(dataBinding.adsKategori, adRequest);
        Common.setupRefresh(dataBinding.refreshKategori);

        layoutManager = new LinearLayoutManager(this);
        tagLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        Common.setupRecyclerView(dataBinding.recylerTag, tagLayoutManager);
        Common.setupRecyclerView(dataBinding.recylerKategori, layoutManager);

        Common.closeAds(dataBinding.closeAds, dataBinding.adsKategori, dataBinding.slidingKategori);
        Common.adViewListener(dataBinding.adsKategori);

        dataBinding.slidingKategori.setTouchEnabled(false);

        presenter = new KategoriTagPresenterImpl(this);
        presenter.setQuery("aaaa");
        presenter.running();

        adapter = new KategoriAdapter();
        tagAdapter = new KategoriTagAdapter();
        dataBinding.recylerKategori.setAdapter(adapter);
        dataBinding.recylerTag.setAdapter(tagAdapter);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(KategoriListViewModel.class);
        viewModel.setPage(page);
        viewModel.getKategori().observe(this, listResource -> {
            if (listResource == null) {
                adapter.replace(null, dialog);
            } else {
                adapter.replace(listResource.data, dialog);
            }
        });

        save_and_del = ViewModelProviders.of(this, viewModelFactory).get(SaveandDelBookmarkViewModel.class);

        adapter.setOnSourceItemClickListener((v, position, response) -> {
            Toast.makeText(this, response.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            Common.trackEvent("List " + TAG, "click", response.get(position).getTitle_link());
            String[] key = {"title", "launch_url"};
            String[] value = {response.get(position).getTitle(), response.get(position).getTitle_link()};
            Common.newActivity(Detail.class, key, value);
        });
        adapter.setEntityOnSourceItemClickListener((v, position, response) -> {
            Common.showToast("Bookmark clicked");
            KategoriEntity entity = response.get(position);
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
        dataBinding.recylerKategori.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                    dataBinding.refreshKategori.setRefreshing(true);
                    page = page + 1;
                    viewModel.setPage(page);
                    viewModel.getKategori().observe(Kategori.this, listResource -> {
                        if (listResource == null) {
                            adapter.replace(null, dataBinding.refreshKategori);
                        } else {
                            adapter.replace(listResource.data, dataBinding.refreshKategori);
                        }
                    });
                }
            }
        });
        dataBinding.refreshKategori.setOnRefreshListener(() -> {
            dataBinding.refreshKategori.setRefreshing(true);
            viewModel.setPage(page);
            viewModel.getKategori().observe(this, listResource -> {
                if (listResource == null) {
                    adapter.replace(null, dataBinding.refreshKategori);
                } else {
                    adapter.replace(listResource.data, dataBinding.refreshKategori);
                }
            });
        });
    }

    @Override
    public void onPause() {
        dataBinding.adsKategori.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBinding.adsKategori.resume();
        Common.trackScreenView(TAG + " - " + kate);
        Common.slidingCollapse(dataBinding.slidingKategori);
    }

    @Override
    public boolean onSupportNavigateUp() {
        SharedPref.putBoolean(Constants.AUTH.KATEGORI_OPEN, true);
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        SharedPref.putBoolean(Constants.AUTH.KATEGORI_OPEN, true);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        SharedPref.putBoolean(Constants.AUTH.KATEGORI_OPEN, true);
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        dataBinding.refreshKategori.setRefreshing(true);
    }

    @Override
    public void showData(List<KategoriTagEntity> entities) {
        tagAdapter.clear();
        tagAdapter.setData(entities);
        tagAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Common.showToast(message);
    }

    @Override
    public void showSuccessfully() {
        dataBinding.refreshKategori.setRefreshing(false);
    }
}