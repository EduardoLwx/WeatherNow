package com.example.weathernow;

import com.example.weathernow.models.localization.Localization;
import com.example.weathernow.models.localization.LocalizationModule;
import com.example.weathernow.models.weather.Weather;
import com.example.weathernow.models.weather.WeatherModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by eduardo on 01/10/17.
 *
 * Application models injector
 */

@Singleton
@Component(modules = {WeatherModule.class, LocalizationModule.class})
public interface AppComponent {

    // Make Weather.service injectable for dependent Components
    Weather.Service weatherService();

    // Make Localization.Service injectable for dependent Components
    Localization.Service localizationService();

}
