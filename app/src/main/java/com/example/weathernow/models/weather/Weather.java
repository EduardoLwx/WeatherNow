package com.example.weathernow.models.weather;

import android.location.Location;

/**
 * Created by eduardo on 01/10/17.
 *
 * Contract for Weather.Service and Weather.Callback
 */

public interface Weather {
    interface Service {
        void requestWeather(Callback callback, Location location);
    }

    interface Callback {
        void onMissingInternetConnection();

        void onRequestWeatherFail(String error);

        void onRequestWeatherSuccess(String temperature, String status, String
                location);
    }
}
