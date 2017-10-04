package com.example.weathernow;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eduardo on 02/10/17.
 */

@Module
public class AppModule {
    private Application mApplication;

    AppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return mApplication;
    }
}
