package com.hafiizh.japanesestation.data.repository;

import android.arch.lifecycle.LiveData;

import com.hafiizh.japanesestation.data.local.dao.BookmarkDao;
import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 11/2/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class BookmarkRepository {
    private BookmarkDao bookmarkDao;

    @Inject
    public BookmarkRepository(BookmarkDao bookmarkDao) {
        this.bookmarkDao = bookmarkDao;
    }

    public LiveData<List<BookmarkEntity>> getBookmark() {
        return bookmarkDao.loadBookmark();
    }
}