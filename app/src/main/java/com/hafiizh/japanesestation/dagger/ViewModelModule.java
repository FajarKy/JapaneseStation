package com.hafiizh.japanesestation.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.hafiizh.japanesestation.ui.activity.bookmark.BookmarkListViewModel;
import com.hafiizh.japanesestation.ui.activity.bookmark.SaveandDelBookmarkViewModel;
import com.hafiizh.japanesestation.ui.activity.detail.DetailViewModel;
import com.hafiizh.japanesestation.ui.activity.kategori.KategoriListViewModel;
import com.hafiizh.japanesestation.ui.fragment.hot.HotListViewModel;
import com.hafiizh.japanesestation.ui.fragment.latest.LatestListViewModel;
import com.hafiizh.japanesestation.ui.fragment.popular.PopularListViewModel;
import com.hafiizh.japanesestation.ui.fragment.trending.TrendingListViewModel;
import com.hafiizh.japanesestation.viewmodel.AppViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LatestListViewModel.class)
    abstract ViewModel bindsLatestListVM(LatestListViewModel latestListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PopularListViewModel.class)
    abstract ViewModel bindsPopularListVM(PopularListViewModel popularListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TrendingListViewModel.class)
    abstract ViewModel bindsTrendingListVM(TrendingListViewModel trendingListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HotListViewModel.class)
    abstract ViewModel bindsHotListVM(HotListViewModel hotListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindsDetailVM(DetailViewModel detailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(KategoriListViewModel.class)
    abstract ViewModel bindsKategoriListVM(KategoriListViewModel kategoriListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkListViewModel.class)
    abstract ViewModel bindsBookmarkListVM(BookmarkListViewModel bookmarkListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SaveandDelBookmarkViewModel.class)
    abstract ViewModel bindsSaveandDelBookmark(SaveandDelBookmarkViewModel saveandDelBookmarkViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(AppViewModelFactory appViewModelFactory);
}