package com.hafiizh.japanesestation.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;

import java.util.List;

/**
 * Created by HAFIIZH on 11/2/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Dao
public interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    LiveData<List<BookmarkEntity>> loadBookmark();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBookmark(BookmarkEntity entity);

    @Delete
    void deleteBookmark(BookmarkEntity entity);
}
