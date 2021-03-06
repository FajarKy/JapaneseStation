package com.hafiizh.japanesestation.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hafiizh.japanesestation.data.local.entity.HotEntity;

import java.util.List;

/**
 * Created by HAFIIZH on 10/24/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Dao
public interface HotDao {
    @Query("SELECT * FROM hot")
    LiveData<List<HotEntity>> loadHot();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveHot(List<HotEntity> hotEntities);

    @Query("DELETE FROM hot")
    void deleteHot();
}
