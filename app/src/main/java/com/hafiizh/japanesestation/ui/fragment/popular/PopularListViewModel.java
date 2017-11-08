package com.hafiizh.japanesestation.ui.fragment.popular;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.entity.PopularEntity;
import com.hafiizh.japanesestation.data.repository.PopularRepository;
import com.hafiizh.japanesestation.util.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class PopularListViewModel extends ViewModel {
    @VisibleForTesting
    private MutableLiveData<Integer> page = new MutableLiveData<>();
    private final LiveData<Resource<List<PopularEntity>>> popular;

    @Inject
    public PopularListViewModel(PopularRepository popularRepository) {
        popular = Transformations.switchMap(page, data -> {
            if (data == null) {
                return AbsentLiveData.create();
            } else {
                return popularRepository.loadPopular(data);
            }
        });
    }

    @VisibleForTesting
    public void setPage(int page) {
        this.page.setValue(page);
    }

    public LiveData<Resource<List<PopularEntity>>> getPopular() {
        return popular;
    }
}