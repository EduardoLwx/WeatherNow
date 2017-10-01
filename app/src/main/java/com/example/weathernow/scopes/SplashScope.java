package com.example.weathernow.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by eduardo on 01/10/17.
 *
 * Dagger Scope for SplashScreen
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SplashScope {

}
