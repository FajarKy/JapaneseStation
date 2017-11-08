package com.hafiizh.japanesestation.data.remote;

import com.hafiizh.japanesestation.data.remote.model.DetailResponse;
import com.hafiizh.japanesestation.data.remote.model.HotResponse;
import com.hafiizh.japanesestation.data.remote.model.KategoriResponse;
import com.hafiizh.japanesestation.data.remote.model.KategoriTagResponse;
import com.hafiizh.japanesestation.data.remote.model.LatestResponse;
import com.hafiizh.japanesestation.data.remote.model.PopularResponse;
import com.hafiizh.japanesestation.data.remote.model.TrendingResponse;
import com.hafiizh.japanesestation.utilities.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public interface AppService {
    @GET(Constants.API.POST)
    Call<LatestResponse> loadLatest(
            @Query("page") int page
    );

    @GET(Constants.API.POPULAR)
    Call<PopularResponse> loadPopular(
            @Query("page") int page
    );

    @GET(Constants.API.HOT)
    Call<HotResponse> loadHot(
            @Query("page") int page
    );

    @GET(Constants.API.TRENDING)
    Call<TrendingResponse> loadTrending(
            @Query("page") int page
    );

    @GET(Constants.API.KATEGORI)
    Call<KategoriResponse> loadKategori(
            @Query("kategori") String kategori,
            @Query("page") int page
    );

    @GET(Constants.API.DETAIL)
    Call<DetailResponse> loadDetail(
            @Query("url") String url
    );

    @GET(Constants.API.SEARCH)
    Call<KategoriTagResponse> getTagResponse(
            @Query("s") String query
    );
}