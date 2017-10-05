package com.example.weathernow.models.localization;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eduardo on 01/10/17.
 *
 * Dagger Module that resolves the injection of the Localization.Service
 */

@Module
public class LocalizationModule {
    @Provides
    @Singleton
    Localization.Service providerLocalizationService(Application application){
        return new LocalizationService(application);
    }
}
