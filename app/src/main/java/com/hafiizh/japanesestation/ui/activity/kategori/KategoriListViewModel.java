package com.hafiizh.japanesestation.ui.activity.kategori;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.hafiizh.japanesestation.data.Resource;
import com.hafiizh.japanesestation.data.local.entity.KategoriEntity;
import com.hafiizh.japanesestation.data.repository.KategoriRepository;
import com.hafiizh.japanesestation.util.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by HAFIIZH on 10/25/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class KategoriListViewModel extends ViewModel {
    private MutableLiveData<Integer> page = new MutableLiveData<>();
    private LiveData<Resource<List<KategoriEntity>>> kategori;

    @Inject
    public KategoriListViewModel(KategoriRepository repository) {
        kategori = Transformations.switchMap(page, data -> {
            if (data == null) {
                return AbsentLiveData.create();
            } else {
                return repository.loadKategori(Kategori.kate, data);
            }
        });
    }

    public void setPage(int page) {
        this.page.setValue(page);
    }

    public LiveData<Resource<List<KategoriEntity>>> getKategori() {
        return kategori;
    }
}