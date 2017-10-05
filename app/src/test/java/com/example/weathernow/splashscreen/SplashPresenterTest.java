package com.example.weathernow.splashscreen;

import android.location.Location;

import com.example.weathernow.models.localization.Localization;
import com.example.weathernow.models.weather.Weather;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

/**
 * Created by eduardo on 01/10/17.
 *
 * Tests for SplashPresenter
 */

public class SplashPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Splash.View splashView;

    @Mock
    Weather.Service weatherService;

    @Mock
    Localization.Service localizationService;

    private SplashPresenter splashPresenter;

    @Before
    public void setUp() throws Exception {
        splashPresenter = new SplashPresenter(splashView,
                localizationService, weatherService);
    }

//  Splash.Presenter  //////////////////////////////////////////////////////////
    @Test
    public void testRequestLocal(){
        splashPresenter.requestLocal();
        verify(splashView).showProgressGettingLocation();
        verify(localizationService).requestLocation(splashPresenter);
    }

    @Test
    public void testRequestWeather(){
        Location location1 = new Location("");
        splashPresenter.requestWeather(location1);
        verify(splashView).showProgressGettingWeather();
        verify(weatherService).requestWeather(splashPresenter, location1);
    }


//  Localization.Callback  /////////////////////////////////////////////////////
    @Test
    public void testOnRequestLocationSuccess(){
        Location location = new Location("");
        splashPresenter.onRequestLocationSuccess(location);
        verify(splashView).onGettingLocationFinish(location);
    }

    @Test
    public void testOnRequestLocationFail(){
        String error = "Some Error";
        splashPresenter.onRequestLocationFail(error);
        verify(splashView).handleGettingLocationError(error);
    }


//  Weather.Callback  //////////////////////////////////////////////////////////
    @Test
    public void testOnMissingInternetConnection(){
        splashPresenter.onMissingInternetConnection();
        verify(splashView).handleNoInternetConnectionError();
    }

    @Test
    public void testOnRequestWeatherFail(){
        String error = "Some Error";
        splashPresenter.onRequestWeatherFail(error);
        verify(splashView).handleGettingWeatherError(error);
    }

    @Test
    public void testOnRequestWeatherSuccess(){
        String temperature = "28c";
        String status = "Parcialmente nublado";
        String location = "Fortaleza - CE";
        splashPresenter.onRequestWeatherSuccess(temperature, status, location);
        verify(splashView).navigateToMainScreen(temperature, status, location);
    }

}
