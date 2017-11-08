package com.hafiizh.japanesestation.data.repository.kategori;

import com.hafiizh.japanesestation.data.local.entity.KategoriTagEntity;
import com.hafiizh.japanesestation.data.remote.AppService;

import java.util.List;

/**
 * Created by HAFIIZH on 11/7/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class KategoriTagPresenterImpl implements KategoriTagPresenter, KategoriTagInteractor.OnRunCreateCallListener {
    private KategoriTagView kategoriTagView;
    private KategoriTagInteractor kategoriTagInteractor;
    private String query;
    private AppService appService;

    public KategoriTagPresenterImpl(KategoriTagView kategoriTagView) {
        this.kategoriTagView = kategoriTagView;
        this.kategoriTagInteractor = new KategoriTagInteractorImpl();
    }

    @Override
    public void validateCredentials(AppService appService) {
        if (kategoriTagView != null)
            kategoriTagView.showProgress();
        this.appService = appService;
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void running() {
        kategoriTagInteractor.run(appService, query, this);
    }

    @Override
    public void onSource(List<KategoriTagEntity> entities) {
        if (kategoriTagView != null)
            kategoriTagView.showData(entities);
    }

    @Override
    public void onSuccessfully() {
        if (kategoriTagView != null)
            kategoriTagView.showSuccessfully();
    }

    @Override
    public void onFailure(String message) {
        if (kategoriTagView != null)
            kategoriTagView.showErrorMessage(message);
    }

    @Override
    public void onProress() {
        if (kategoriTagView != null)
            kategoriTagView.showProgress();
    }
}
