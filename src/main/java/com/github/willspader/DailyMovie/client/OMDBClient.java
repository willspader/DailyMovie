package com.github.willspader.DailyMovie.client;

import com.github.willspader.DailyMovie.dto.OmdbDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBClient {

    @GET(".")
    Call<OmdbDTO> getById(@Query("i") String id,
                          @Query("apikey") String apikey);

}
