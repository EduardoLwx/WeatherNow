package com.example.weathernow.splashscreen;

import android.location.Location;

import com.example.weathernow.models.localization.Localization;
import com.example.weathernow.models.weather.Weather;

/**
 * Created by eduardo on 01/10/17.
 *
 * Presenter for SplashScreen
 */

class SplashPresenter implements Splash.Presenter,
        Localization.Callback, Weather.Callback {

    private Splash.View mSplashView;
    private Localization.Service mLocalizationService;
    private Weather.Service mWeatherService;

    SplashPresenter(Splash.View splashView,
                    Localization.Service localizationService,
                    Weather.Service weatherService){

        mSplashView = splashView;
        mLocalizationService = localizationService;
        mWeatherService = weatherService;
    }


//  Splash.Presenter  //////////////////////////////////////////////////
    @Override
    public void requestLocal() {
        mSplashView.showProgressGettingLocation();
        mLocalizationService.requestLocation(this);
    }

    @Override
    public void requestWeather(Location location) {
        mSplashView.showProgressGettingWeather();
        mWeatherService.requestWeather(this, location);
    }

    //  Localization.Callback  /////////////////////////////////////////////////////
    @Override
    public void handleMissingLocationPermission() {
        mSplashView.handleNoLocationPermissionError();
    }

    @Override
    public void onRequestLocationSuccess(Location location) {
        mSplashView.onGettingLocationFinish(location);
    }

    @Override
    public void onRequestLocationFail(String error) {
        mSplashView.handleGettingLocationError(error);
    }

//  Weather.Callback  //////////////////////////////////////////////////////////
    @Override
    public void onMissingInternetConnection() {
        mSplashView.handleNoInternetConnectionError();
    }

    @Override
    public void onRequestWeatherFail(String error) {
        mSplashView.handleGettingWeatherError(error);
    }

    @Override
    public void onRequestWeatherSuccess(String temperature, String status,
                                        String location) {
        mSplashView.navigateToMainScreen(temperature, status, location);
    }

}
