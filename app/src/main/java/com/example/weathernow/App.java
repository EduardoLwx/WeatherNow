package com.example.weathernow;

import android.app.Application;

import com.example.weathernow.models.localization.LocalizationModule;
import com.example.weathernow.models.weather.WeatherModule;

/**
 * Created by eduardo on 01/10/17.
 *
 * App replaces the default Application
 */

public class App extends Application{
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .localizationModule(new LocalizationModule())
                .weatherModule(new WeatherModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
