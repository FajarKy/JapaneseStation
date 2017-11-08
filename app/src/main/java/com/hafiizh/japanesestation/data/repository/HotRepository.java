package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.data.NetworkBoundResource;
import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.dao.HotDao;
import com.hafiizh.japanesestation.data.local.entity.HotEntity;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.HotResponse;
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

public class HotRepository {
    private final HotDao hotDao;
    private final AppService appService;

    @Inject
    public HotRepository(HotDao hotDao, AppService appService) {
        this.hotDao = hotDao;
        this.appService = appService;
    }

    public LiveData<Resource<List<HotEntity>>> loadHot(int page) {
        return new NetworkBoundResource<List<HotEntity>, HotResponse>() {
            @Override
            protected void delete() {
                if (SharedPref.getBoolean(Constants.AUTH.HOT_OPEN)) {
                    hotDao.deleteHot();
                    SharedPref.putBoolean(Constants.AUTH.HOT_OPEN, false);
                }
            }

            @Override
            protected void saveCallResult(@NonNull HotResponse item) {
                hotDao.saveHot(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<List<HotEntity>> loadLocalDB() {
                return hotDao.loadHot();
            }

            @NonNull
            @Override
            protected Call<HotResponse> createCall() {
                return appService.loadHot(page);
            }
        }.getAsLiveData();
    }
}