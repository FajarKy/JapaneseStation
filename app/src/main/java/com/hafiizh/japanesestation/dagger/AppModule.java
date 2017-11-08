package com.hafiizh.japanesestation.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.hafiizh.japanesestation.data.local.AppDatabase;
import com.hafiizh.japanesestation.data.local.dao.BookmarkDao;
import com.hafiizh.japanesestation.data.local.dao.DetailDao;
import com.hafiizh.japanesestation.data.local.dao.HotDao;
import com.hafiizh.japanesestation.data.local.dao.KategoriDao;
import com.hafiizh.japanesestation.data.local.dao.LatestDao;
import com.hafiizh.japanesestation.data.local.dao.PopularDao;
import com.hafiizh.japanesestation.data.local.dao.TrendingDao;
import com.hafiizh.japanesestation.data.remote.AppService;
import com.hafiizh.japanesestation.data.remote.RequestInterceptor;
import com.hafiizh.japanesestation.utilities.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

@Module(includes = ViewModelModule.class)
public class AppModule {
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor())
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.API.BASE_API_V2)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        //                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
    }

    @Provides
    @Singleton
    AppService provideLatestService(Retrofit retrofit) {
        return retrofit.create(AppService.class);
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "js").build();
    }

    @Provides
    @Singleton
    LatestDao provideLatestDao(AppDatabase appDatabase) {
        return appDatabase.latestDao();
    }

    @Provides
    @Singleton
    PopularDao providePopularDao(AppDatabase appDatabase) {
        return appDatabase.popularDao();
    }

    @Provides
    @Singleton
    HotDao provideHotDao(AppDatabase appDatabase) {
        return appDatabase.hotDao();
    }

    @Provides
    @Singleton
    TrendingDao provideTrendingDao(AppDatabase appDatabase) {
        return appDatabase.trendingDao();
    }

    @Provides
    @Singleton
    KategoriDao provideKategoriDao(AppDatabase appDatabase) {
        return appDatabase.kategoriDao();
    }

    @Provides
    @Singleton
    DetailDao provideDetailDao(AppDatabase appDatabase) {
        return appDatabase.detailDao();
    }

    @Provides
    @Singleton
    BookmarkDao provideBookmarkDao(AppDatabase appDatabase) {
        return appDatabase.bookmarkDao();
    }
}