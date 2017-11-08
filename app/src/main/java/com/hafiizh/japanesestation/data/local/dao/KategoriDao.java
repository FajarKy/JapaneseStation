package com.hafiizh.japanesestation.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hafiizh.japanesestation.data.local.entity.KategoriEntity;

import java.util.List;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Dao
public interface KategoriDao {
    @Query("SELECT * FROM kategori")
    LiveData<List<KategoriEntity>> loadKategori();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveKategori(List<KategoriEntity> kategoriEntities);

    @Query("DELETE FROM kategori")
    void deleteKategori();
}
