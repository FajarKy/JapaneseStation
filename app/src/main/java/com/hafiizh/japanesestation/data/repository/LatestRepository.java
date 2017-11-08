package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.data.NetworkBoundResource;
import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.dao.LatestDao;
import com.hafiizh.japanesestation.data.local.entity.LatestEntity;;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.LatestResponse;
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

public class LatestRepository {
    private final LatestDao latestDao;
    private final AppService appService;

    @Inject
    public LatestRepository(LatestDao latestDao, AppService appService) {
        this.latestDao = latestDao;
        this.appService = appService;
    }

    public LiveData<Resource<List<LatestEntity>>> latest(int page) {
        return new NetworkBoundResource<List<LatestEntity>, LatestResponse>() {
            @Override
            protected void delete() {
                if (SharedPref.getAppOpen(Constants.AUTH.LATEST_OPEN)) {
                    latestDao.deleteLatest();
                    SharedPref.putAppOpen(Constants.AUTH.LATEST_OPEN, false);
                }
            }

            @Override
            protected void saveCallResult(@NonNull LatestResponse item) {
                latestDao.saveLatest(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<List<LatestEntity>> loadLocalDB() {
                return latestDao.loadLatest();
            }

            @NonNull
            @Override
            protected Call<LatestResponse> createCall() {
                return appService.loadLatest(page);
            }
        }.getAsLiveData();
    }
}