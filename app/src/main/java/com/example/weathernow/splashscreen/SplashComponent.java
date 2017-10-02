package com.example.weathernow.splashscreen;

import com.example.weathernow.AppComponent;
import com.example.weathernow.scopes.SplashScope;

import dagger.Component;

/**
 * Created by eduardo on 01/10/17.
 *
 * SplashScreen MVP Injector
 */

@SplashScope
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
interface SplashComponent {
    void inject(SplashActivity activity);
}
