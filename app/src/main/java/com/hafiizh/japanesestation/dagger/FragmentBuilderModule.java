package com.hafiizh.japanesestation.dagger;

import com.hafiizh.japanesestation.ui.fragment.hot.HotFragment;
import com.hafiizh.japanesestation.ui.fragment.latest.LatestFragment;
import com.hafiizh.japanesestation.ui.fragment.popular.PopularFragment;
import com.hafiizh.japanesestation.ui.fragment.trending.TrendingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract LatestFragment contributeLatestFragment();

    @ContributesAndroidInjector
    abstract PopularFragment contributePopularFragment();

    @ContributesAndroidInjector
    abstract TrendingFragment contributeTrendingFragment();

    @ContributesAndroidInjector
    abstract HotFragment contributeHotFragment();
}