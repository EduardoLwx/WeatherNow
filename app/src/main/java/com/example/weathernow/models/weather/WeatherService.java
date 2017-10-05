package com.example.weathernow.models.weather;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.weathernow.models.weather.providers.yahoo.models.Channel;
import com.example.weathernow.models.weather.providers.yahoo.models.RequestResult;
import com.example.weathernow.models.weather.providers.yahoo.remote.YahooWeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eduardo on 01/10/17.
 *
 * Weather.Service Mock
 */

class WeatherService implements Weather.Service {
    private static final String BASE_URL = "https://query.yahooapis.com/";

    private Application mApplication;
    private YahooWeatherService mYahooWeatherService;

    WeatherService(Application application){
        mApplication = application;
        mYahooWeatherService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(YahooWeatherService.class);
    }

    @Override
    public void requestWeather(final Weather.Callback callback, Location
            location) {

        if(isOnline()) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            mYahooWeatherService.getCurrentWeather("select units, location, " +
                    "item.condition from weather.forecast where woeid in " +
                    "(SELECT woeid FROM geo.places WHERE text=\"(" +
                    latitude + "," + longitude +
                    ")\") and u='c' ", "json")
                .enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call,
                                           Response<RequestResult> response) {
                        if (response.isSuccessful()) {
                            Channel data = response.body().getQuery().getResults()
                                    .getChannel();

                            String city = data.getLocation().getCity();
                            String region = data.getLocation().getRegion();

                            String temperature = data.getItem().getCondition
                                    ().getTemp();
                            String unity = data.getUnits().getTemperature();

                            String condition = data.getItem().getCondition().getText();

                            callback.onRequestWeatherSuccess(temperature + "°" + unity,
                                    condition, city + " - " + region);
                        } else {
                            int statusCode = response.code();
                            Log.d("MainActivity", "Request Fail status code: "
                                    + statusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable t) {
                        Log.d("MainActivity", "Request Fail");
                    }
                });
        } else {
            callback.onMissingInternetConnection();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mApplication.getSystemService(Context
                        .CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
