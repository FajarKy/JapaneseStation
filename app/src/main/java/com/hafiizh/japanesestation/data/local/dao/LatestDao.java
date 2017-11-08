package com.hafiizh.japanesestation.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hafiizh.japanesestation.data.local.entity.LatestEntity;

import java.util.List;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Dao
public interface LatestDao {
    @Query("SELECT * FROM latest")
    LiveData<List<LatestEntity>> loadLatest();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveLatest(List<LatestEntity> latestEntities);

    @Query("DELETE FROM latest")
    void deleteLatest();
}
