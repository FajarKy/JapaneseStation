package com.hafiizh.japanesestation.ui.fragment.latest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.entity.LatestEntity;
import com.hafiizh.japanesestation.data.repository.LatestRepository;
import com.hafiizh.japanesestation.util.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class LatestListViewModel extends ViewModel {
    @VisibleForTesting
    private MutableLiveData<Integer> page = new MutableLiveData<>();
    private final LiveData<Resource<List<LatestEntity>>> latest;

    @Inject
    public LatestListViewModel(LatestRepository latestRepository) {
        latest = Transformations.switchMap(page, input -> {
            if (input == null) {
                return AbsentLiveData.create();
            } else {
                return latestRepository.latest(input);
            }
        });
    }

    @VisibleForTesting
    public void setPage(int page) {
        this.page.setValue(page);
    }

    public LiveData<Resource<List<LatestEntity>>> getLatest() {
        return latest;
    }
}