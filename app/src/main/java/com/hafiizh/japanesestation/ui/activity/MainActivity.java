package com.hafiizh.japanesestation.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hafiizh.japanesestation.R;
import com.hafiizh.japanesestation.adapter.ViewPagerAdapter;
import com.hafiizh.japanesestation.base.BaseActivity;
import com.hafiizh.japanesestation.common.DrawerConfig;
import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.databinding.ActivityMainBinding;
import com.hafiizh.japanesestation.ui.fragment.hot.HotFragment;
import com.hafiizh.japanesestation.ui.fragment.latest.LatestFragment;
import com.hafiizh.japanesestation.ui.fragment.trending.TrendingFragment;
import com.hafiizh.japanesestation.utilities.Constants;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    DrawerConfig drawer;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Toolbar getToolbar() {
        return dataBinding.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = new DrawerConfig(this, dataBinding.toolbar);
        drawer.addDrawer();
        setupVP(dataBinding.mainViewPager);
        dataBinding.tab.setupWithViewPager(dataBinding.mainViewPager);
        setupTabIcon();
        dataBinding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        LatestFragment.recyclerView.smoothScrollToPosition(0);
                        break;
                    case 1:
                        TrendingFragment.recyclerView.smoothScrollToPosition(0);
                        break;
                    case 2:
                        HotFragment.recyclerView.smoothScrollToPosition(0);
                        break;
                }
            }
        });
    }

    private void setupTabIcon() {
        dataBinding.tab.getTabAt(0).setIcon(R.mipmap.ic_launcher);
        dataBinding.tab.getTabAt(1).setIcon(R.mipmap.ic_launcher_round);
        dataBinding.tab.getTabAt(2).setIcon(R.mipmap.ic_launcher);
    }

    private void setupVP(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addTab(new LatestFragment(), Constants.LATEST);
        adapter.addTab(new TrendingFragment(), Constants.TRENDING);
        adapter.addTab(new HotFragment(), Constants.HOT);
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);
        final SearchView search = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isOpenDrawer())
            drawer.closeDrawer();
        else {
            SharedPref.putAppOpen(Constants.AUTH.LATEST_OPEN, true);
            SharedPref.putAppOpen(Constants.AUTH.HOT_OPEN, true);
            SharedPref.putAppOpen(Constants.AUTH.TRENDING_OPEN, true);
            SharedPref.putAppOpen(Constants.AUTH.POPULAR_OPEN, true);
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        SharedPref.putAppOpen(Constants.AUTH.LATEST_OPEN, true);
        SharedPref.putAppOpen(Constants.AUTH.HOT_OPEN, true);
        SharedPref.putAppOpen(Constants.AUTH.TRENDING_OPEN, true);
        SharedPref.putAppOpen(Constants.AUTH.POPULAR_OPEN, true);
        super.onDestroy();
    }
}