package com.example.weathernow.models.weather;

import android.location.Location;
import android.os.Handler;

/**
 * Created by eduardo on 01/10/17.
 *
 * Weather.Service Mock
 */

class WeatherService implements Weather.Service {

    @Override
    public void requestWeather(final Weather.Callback callback, Location
            location) {
        //Mock
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onRequestWeatherSuccess("28C",
                        "Parcialmente nublado", "Fortaleza - CE");
            }
        }, 3000);
    }
}
