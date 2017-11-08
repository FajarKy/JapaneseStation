package com.hafiizh.japanesestation.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hafiizh.japanesestation.data.local.AppDatabase;
import com.hafiizh.japanesestation.data.local.entity.LatestEntity;
import com.hafiizh.japanesestation.data.remote.ApiResponse;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.LatestResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by HAFIIZH on 11/1/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class FetchNextPage implements Runnable {
    private MutableLiveData<Resource<Boolean>> liveData = new MutableLiveData<>();
    private int page;
    private AppService appService;
    private AppDatabase appDatabase;

    public FetchNextPage(int page, AppService appService, AppDatabase appDatabase) {
        this.page = page;
        this.appService = appService;
        this.appDatabase = appDatabase;
    }

    @Override
    public void run() {
        LiveData<List<LatestEntity>> current = appDatabase.latestDao().loadLatest();
        if (current == null) {
            liveData.setValue(null);
            return;
        }
        try {
            Response<LatestResponse> response = appService.loadLatest(page).execute();
            ApiResponse<LatestResponse> apiResponse = new ApiResponse<LatestResponse>(response);
            if (apiResponse.isSuccessful()) {
                try {
                    appDatabase.beginTransaction();
                    appDatabase.latestDao().saveLatest(apiResponse.body.getData());
                    appDatabase.setTransactionSuccessful();
                } finally {
                    appDatabase.endTransaction();
                }
                liveData.postValue(Resource.success(true));
            } else {
                liveData.postValue(Resource.error(apiResponse.errorMessage, true));
            }
        } catch (IOException e) {
            liveData.postValue(Resource.error(e.getMessage(), true));
        }
    }

    public LiveData<Resource<Boolean>> getLiveData() {
        return liveData;
    }
}