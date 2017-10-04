package com.example.weathernow.splashscreen;

import android.location.Location;

/**
 * Created by eduardo on 01/10/17.
 *
 * Contract for view and presenter in SplashScreen MVP
 */

interface Splash {
    interface View {
        void showProgressGettingLocation();

        void handleGettingLocationError(String error);

        void onGettingLocationFinish(Location location);

        void showProgressGettingWeather();

        void handleNoInternetConnectionError();

        void handleGettingWeatherError(String error);

        void navigateToMainScreen(String temperature, String status, String
                location);
    }

    interface Presenter {
        void requestLocal();

        void requestWeather(Location location);
    }
}
