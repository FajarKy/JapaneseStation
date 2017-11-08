package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hafiizh.japanesestation.common.SharedPref;
import com.hafiizh.japanesestation.data.NetworkBoundResource;
import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.dao.KategoriDao;
import com.hafiizh.japanesestation.data.local.entity.KategoriEntity;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.model.KategoriResponse;
import com.hafiizh.japanesestation.utilities.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class KategoriRepository {
    private KategoriDao kategoriDao;
    private AppService appService;

    @Inject
    public KategoriRepository(KategoriDao kategoriDao, AppService appService) {
        this.kategoriDao = kategoriDao;
        this.appService = appService;
    }

    public LiveData<Resource<List<KategoriEntity>>> loadKategori(String kategori, int page) {
        return new NetworkBoundResource<List<KategoriEntity>, KategoriResponse>() {
            @Override
            protected void delete() {
                if (SharedPref.getBoolean(Constants.AUTH.KATEGORI_OPEN)) {
                    kategoriDao.deleteKategori();
                    SharedPref.putBoolean(Constants.AUTH.KATEGORI_OPEN, false);
                }
            }

            @Override
            protected void saveCallResult(@NonNull KategoriResponse item) {
                kategoriDao.saveKategori(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<List<KategoriEntity>> loadLocalDB() {
                return kategoriDao.loadKategori();
            }

            @NonNull
            @Override
            protected Call<KategoriResponse> createCall() {
                return appService.loadKategori(kategori, page);
            }
        }.getAsLiveData();
    }
}