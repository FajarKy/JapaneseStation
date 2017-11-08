package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.data.NetworkBoundResource;
import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.dao.TrendingDao;
import com.hafiizh.japanesestation.data.local.entity.TrendingEntity;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.TrendingResponse;
import com.hafiizh.japanesestation.utilities.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class TrendingRepository {
    private final TrendingDao trendingDao;
    private final AppService appService;

    @Inject
    public TrendingRepository(TrendingDao trendingDao, AppService appService) {
        this.trendingDao = trendingDao;
        this.appService = appService;
    }

    public LiveData<Resource<List<TrendingEntity>>> loadTrending(int page) {
        return new NetworkBoundResource<List<TrendingEntity>, TrendingResponse>() {
            @Override
            protected void delete() {
                if (SharedPref.getBoolean(Constants.AUTH.TRENDING_OPEN)) {
                    trendingDao.deleteTrending();
                    SharedPref.putBoolean(Constants.AUTH.TRENDING_OPEN, false);
                }
            }

            @Override
            protected void saveCallResult(@NonNull TrendingResponse item) {
                trendingDao.saveTrending(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<List<TrendingEntity>> loadLocalDB() {
                return trendingDao.loadTrending();
            }

            @NonNull
            @Override
            protected Call<TrendingResponse> createCall() {
                return appService.loadTrending(page);
            }
        }.getAsLiveData();
    }
}