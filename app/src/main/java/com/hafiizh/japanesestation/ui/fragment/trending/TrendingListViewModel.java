package com.hafiizh.japanesestation.ui.fragment.trending;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.entity.TrendingEntity;
import com.hafiizh.japanesestation.data.repository.TrendingRepository;
import com.hafiizh.japanesestation.util.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class TrendingListViewModel extends ViewModel {
    @VisibleForTesting
    private MutableLiveData<Integer> page = new MutableLiveData<>();
    private final LiveData<Resource<List<TrendingEntity>>> trending;

    @Inject
    public TrendingListViewModel(TrendingRepository repository) {
        trending = Transformations.switchMap(page, data -> {
            if (data == null) {
                return AbsentLiveData.create();
            } else {
                return repository.loadTrending(data);
            }
        });
    }

    @VisibleForTesting
    public void setPage(int page) {
        this.page.setValue(page);
    }

    public LiveData<Resource<List<TrendingEntity>>> getTrending() {
        return trending;
    }
}