package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hafiizh.japanesestation.data.NetworkBoundResource;
import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.dao.DetailDao;
import com.hafiizh.japanesestation.data.local.entity.DetailEntity;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.DetailResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by HAFIIZH on 10/26/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class DetailRepository {
    private DetailDao detailDao;
    private AppService appService;

    @Inject
    public DetailRepository(DetailDao detailDao, AppService appService) {
        this.detailDao = detailDao;
        this.appService = appService;
    }

    public LiveData<Resource<DetailEntity>> loadDetail(String url) {
        return new NetworkBoundResource<DetailEntity, DetailResponse>() {
            @Override
            protected void delete() {
                detailDao.deleteDetail();
            }

            @Override
            protected void saveCallResult(@NonNull DetailResponse item) {
                detailDao.saveDetail(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<DetailEntity> loadLocalDB() {
                return detailDao.loadDetail();
            }

            @NonNull
            @Override
            protected Call<DetailResponse> createCall() {
                return appService.loadDetail(url);
            }
        }.getAsLiveData();
    }
}