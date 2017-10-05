package com.example.weathernow.models.weather.providers.yahoo.remote;

import com.example.weathernow.models.weather.providers.yahoo.models.RequestResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by eduardo on 03/10/17.
 */

public interface YahooWeatherService {
    @GET("/v1/public/yql")
    Call<RequestResult> getCurrentWeather(@Query("q") String query,
                                          @Query("format") String format );
}
