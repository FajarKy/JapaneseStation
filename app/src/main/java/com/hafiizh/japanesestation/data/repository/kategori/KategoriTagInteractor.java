package com.hafiizh.japanesestation.data.repository.kategori;

import com.hafiizh.japanesestation.data.local.entity.KategoriTagEntity;
import com.hafiizh.japanesestation.data.remote.AppService;

import java.util.List;

/**
 * Created by HAFIIZH on 11/7/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public interface KategoriTagInteractor {
    interface OnRunCreateCallListener {
        void onSource(List<KategoriTagEntity> entities);

        void onSuccessfully();

        void onFailure(String message);

        void onProress();
    }

    void run(AppService appService, String query, OnRunCreateCallListener onRunCreateCallListener);
}
