package com.example.weathernow.splashscreen;

import com.example.weathernow.models.localization.Localization;
import com.example.weathernow.models.weather.Weather;
import com.example.weathernow.scopes.SplashScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eduardo on 01/10/17.
 *
 * Dagger Module that resolve the injection of the SplashScreen MVP
 */

@Module
class SplashModule {
    private Splash.View mView;

    SplashModule(Splash.View view){
        mView = view;
    }

    @Provides
    @SplashScope
    Splash.View provideSplashView(){
        return mView;
    }

    @Provides
    @SplashScope
    Splash.Presenter provideSplashPresenter(
            Localization.Service mLocalizationService,
            Weather.Service weatherService){
        return new SplashPresenter(mView, mLocalizationService, weatherService);
    }

}

