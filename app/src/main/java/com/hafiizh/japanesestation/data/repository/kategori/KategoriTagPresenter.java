package com.hafiizh.japanesestation.data.repository.kategori;

import com.hafiizh.japanesestation.data.remote.AppService;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 11/7/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public interface KategoriTagPresenter {
    @Inject
    void validateCredentials(AppService appService);

    void setQuery(String query);

    void running();
}
