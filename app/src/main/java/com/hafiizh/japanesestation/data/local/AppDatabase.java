package com.hafiizh.japanesestation.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.hafiizh.japanesestation.data.local.dao.BookmarkDao;
import com.hafiizh.japanesestation.data.local.dao.DetailDao;
import com.hafiizh.japanesestation.data.local.dao.HotDao;
import com.hafiizh.japanesestation.data.local.dao.KategoriDao;
import com.hafiizh.japanesestation.data.local.dao.LatestDao;
import com.hafiizh.japanesestation.data.local.dao.PopularDao;
import com.hafiizh.japanesestation.data.local.dao.TrendingDao;
import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;
import com.hafiizh.japanesestation.data.local.entity.DetailEntity;
import com.hafiizh.japanesestation.data.local.entity.HotEntity;
import com.hafiizh.japanesestation.data.local.entity.KategoriEntity;
import com.hafiizh.japanesestation.data.local.entity.LatestEntity;
import com.hafiizh.japanesestation.data.local.entity.PopularEntity;
import com.hafiizh.japanesestation.data.local.entity.TrendingEntity;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Database(entities = {
        LatestEntity.class,
        PopularEntity.class,
        HotEntity.class,
        TrendingEntity.class,
        KategoriEntity.class,
        DetailEntity.class,
        BookmarkEntity.class
}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LatestDao latestDao();

    public abstract PopularDao popularDao();

    public abstract HotDao hotDao();

    public abstract TrendingDao trendingDao();

    public abstract KategoriDao kategoriDao();

    public abstract DetailDao detailDao();

    public abstract BookmarkDao bookmarkDao();
}
