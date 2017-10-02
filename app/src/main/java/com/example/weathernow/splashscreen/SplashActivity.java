package com.example.weathernow.splashscreen;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weathernow.App;
import com.example.weathernow.R;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements Splash.View {

    private TextView mTextLoadingProgress;
    private TextView mTextError;

    @Inject
    protected Splash.Presenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTextLoadingProgress = (TextView)findViewById(R.id.text_loading_progress);
        mTextError = (TextView)findViewById(R.id.text_error);

        DaggerSplashComponent.builder()
                .appComponent(((App)getApplicationContext()).getAppComponent())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        mSplashPresenter.requestLocal();
    }


//  Splash.View  ///////////////////////////////////////////////////////////////
    @Override
    public void showProgressGettingLocation() {
        mTextLoadingProgress.setText(R.string.msg_loading_location);
    }

    @Override
    public void handleGettingLocationError(String error) {
        mTextLoadingProgress.setText("");
        mTextError.setText(R.string.msg_error_loading_location);
    }

    @Override
    public void handleNoLocationPermissionError() {
        mTextLoadingProgress.setText("");
        mTextError.setText(R.string.msg_error_no_permission_location);
    }

    @Override
    public void onGettingLocationFinish(Location location) {
        mSplashPresenter.requestWeather(location);
    }

    @Override
    public void showProgressGettingWeather() {
        mTextLoadingProgress.setText(R.string.msg_loading_weather);
    }

    @Override
    public void handleNoInternetConnectionError() {
        mTextLoadingProgress.setText("");
        mTextError.setText(R.string.msg_error_no_internet_connection);
    }

    @Override
    public void handleGettingWeatherError(String error) {
        mTextLoadingProgress.setText("");
        mTextError.setText(R.string.msg_error_loading_weather);
    }

    @Override
    public void navigateToMainScreen(String temperature, String status, String
                                     location) {
        Toast.makeText(this, temperature + ", " + status + " em " + location ,
                Toast.LENGTH_LONG).show();
    }

}
