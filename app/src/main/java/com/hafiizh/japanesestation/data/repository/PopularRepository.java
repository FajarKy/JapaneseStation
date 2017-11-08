package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.data.NetworkBoundResource;
import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.dao.PopularDao;
import com.hafiizh.japanesestation.data.local.entity.PopularEntity;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.PopularResponse;
import com.hafiizh.japanesestation.utilities.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class PopularRepository {
    private final PopularDao popularDao;
    private final AppService appService;

    @Inject
    public PopularRepository(PopularDao tabDao, AppService appService) {
        this.popularDao = tabDao;
        this.appService = appService;
    }

    public LiveData<Resource<List<PopularEntity>>> loadPopular(int page) {
        return new NetworkBoundResource<List<PopularEntity>, PopularResponse>() {
            @Override
            protected void delete() {
                if (SharedPref.getBoolean(Constants.AUTH.POPULAR_OPEN)) {
                    popularDao.deletePopular();
                    SharedPref.putBoolean(Constants.AUTH.POPULAR_OPEN, false);
                }
            }

            @Override
            protected void saveCallResult(@NonNull PopularResponse item) {
                popularDao.savePopular(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<List<PopularEntity>> loadLocalDB() {
                return popularDao.loadPopular();
            }

            @NonNull
            @Override
            protected Call<PopularResponse> createCall() {
                return appService.loadPopular(page);
            }
        }.getAsLiveData();
    }
}