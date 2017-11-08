package com.hafiizh.japanesestation.data.repository.kategori;

import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.KategoriTagResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HAFIIZH on 11/7/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class KategoriTagInteractorImpl implements KategoriTagInteractor {

    @Override
    public void run(AppService appService, String query, OnRunCreateCallListener onRunCreateCallListener) {
        onRunCreateCallListener.onProress();
        appService.getTagResponse(query).enqueue(new Callback<KategoriTagResponse>() {
            @Override
            public void onResponse(Call<KategoriTagResponse> call, Response<KategoriTagResponse> response) {
                if (response.isSuccessful()) {
                    onRunCreateCallListener.onSource(response.body().getData());
                    onRunCreateCallListener.onSuccessfully();
                }
            }

            @Override
            public void onFailure(Call<KategoriTagResponse> call, Throwable t) {
                onRunCreateCallListener.onFailure("" + t.getMessage());
            }
        });
    }
}
