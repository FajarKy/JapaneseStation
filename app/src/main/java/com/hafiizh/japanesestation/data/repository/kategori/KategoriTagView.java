package com.hafiizh.japanesestation.data.repository.kategori;

import com.hafiizh.japanesestation.data.local.entity.KategoriTagEntity;

import java.util.List;

/**
 * Created by HAFIIZH on 11/7/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public interface KategoriTagView {
    void showProgress();

    void showData(List<KategoriTagEntity> entities);

    void showErrorMessage(String message);

    void showSuccessfully();
}