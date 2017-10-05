package com.example.weathernow.models.weather;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eduardo on 01/10/17.
 *
 * Dagger Module that resolves the injection of the Weather.Service
 */

@Module
public class WeatherModule {

    @Provides
    @Singleton
    Weather.Service providerWeatherService(Application application){
        return new WeatherService(application);
    }

}
