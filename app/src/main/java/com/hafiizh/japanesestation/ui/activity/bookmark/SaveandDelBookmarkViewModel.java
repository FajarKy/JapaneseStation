package com.hafiizh.japanesestation.ui.activity.bookmark;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.hafiizh.japanesestation.data.local.AppDatabase;
import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 11/3/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class SaveandDelBookmarkViewModel extends ViewModel {
    private AppDatabase appDatabase;

    @Inject
    public SaveandDelBookmarkViewModel(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void saveBookmark(BookmarkEntity entity) {
        new SaveAsyncTask(appDatabase).execute(entity);
    }

    public void deleteBookmark(BookmarkEntity entity) {
        new DeleteAsyncTask(appDatabase).execute(entity);
    }

    @SuppressLint("StaticFieldLeak")
    public class SaveAsyncTask extends AsyncTask<BookmarkEntity, Void, Void> {
        private AppDatabase appDatabase;

        public SaveAsyncTask(AppDatabase appDatabase) {
            this.appDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(BookmarkEntity... bookmarkEntities) {
            appDatabase.bookmarkDao().saveBookmark(bookmarkEntities[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class DeleteAsyncTask extends AsyncTask<BookmarkEntity, Void, Void> {
        private AppDatabase appDatabase;

        public DeleteAsyncTask(AppDatabase appDatabase) {
            this.appDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(BookmarkEntity... bookmarkEntities) {
            appDatabase.bookmarkDao().deleteBookmark(bookmarkEntities[0]);
            return null;
        }
    }
}