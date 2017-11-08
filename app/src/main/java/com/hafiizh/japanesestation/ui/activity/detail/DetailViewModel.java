package com.hafiizh.japanesestation.ui.activity.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.entity.DetailEntity;
import com.hafiizh.japanesestation.data.repository.DetailRepository;
import com.hafiizh.japanesestation.util.AbsentLiveData;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 10/26/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class DetailViewModel extends ViewModel {
    private MutableLiveData<String> url = new MutableLiveData<>();
    private LiveData<Resource<DetailEntity>> detail;

    @Inject
    public DetailViewModel(DetailRepository repository) {
        detail = Transformations.switchMap(url, data -> {
            if (data == null) {
                return AbsentLiveData.create();
            } else {
                return repository.loadDetail(data);
            }
        });
    }

    public void setUrl(String url) {
        this.url.setValue(url);
    }

    public LiveData<Resource<DetailEntity>> getDetail() {
        return detail;
    }
}