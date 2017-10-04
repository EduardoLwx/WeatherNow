package com.example.weathernow.splashscreen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.weathernow.App;
import com.example.weathernow.R;
import com.example.weathernow.weatherScreen.WeatherActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements Splash.View {
    private final int REQUEST_LOCATION_PERMISSION = 23;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean permissionCoarseLocation = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager
                .PERMISSION_GRANTED;
        boolean permissionFineLocation = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager
                .PERMISSION_GRANTED;

        if(!permissionCoarseLocation || !permissionFineLocation){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                mTextLoadingProgress.setText("");
                mTextError.setText(R.string.msg_error_no_permission_location);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);
            }
        } else {
            mSplashPresenter.requestLocal();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mSplashPresenter.requestLocal();
                } else {
                    mTextLoadingProgress.setText("");
                    mTextError.setText(R.string.msg_error_no_permission_location);
                }
                break;
        }
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
        startActivity(
                WeatherActivity.getIntent(this, temperature, status, location));
    }

}
