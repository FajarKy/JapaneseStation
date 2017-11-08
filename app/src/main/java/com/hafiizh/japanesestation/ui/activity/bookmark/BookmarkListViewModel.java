package com.hafiizh.japanesestation.ui.activity.bookmark;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.hafiizh.japanesestation.data.local.entity.BookmarkEntity;
import com.hafiizh.japanesestation.data.repository.BookmarkRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 11/3/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class BookmarkListViewModel extends ViewModel {
    private LiveData<List<BookmarkEntity>> bookmark;

    @Inject
    public BookmarkListViewModel(BookmarkRepository repository) {
        bookmark = repository.getBookmark();
    }

    public LiveData<List<BookmarkEntity>> getBookmark() {
        return bookmark;
    }
}