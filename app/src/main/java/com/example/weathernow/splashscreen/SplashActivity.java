package com.example.weathernow.splashscreen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weathernow.App;
import com.example.weathernow.R;
import com.example.weathernow.weatherScreen.WeatherActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements Splash.View {
    private final int REQUEST_LOCATION_PERMISSION = 23;

    private TextView mTextLoadingProgress;
    private TextView mTextError;
    private Button mButtonTryAgain;

    @Inject
    protected Splash.Presenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTextLoadingProgress = findViewById(R.id.text_loading_progress);
        mTextError = findViewById(R.id.text_error);
        mButtonTryAgain = findViewById(R.id.button_try_again);

        DaggerSplashComponent.builder()
                .appComponent(((App)getApplicationContext()).getAppComponent())
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //   Needed because sometimes is so fast that splash
        // screen appears to be broke
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDataFromServices();
            }
        }, 1000);
    }


    private void getDataFromServices(){
        super.onResume();
        setStringProgress(R.string.msg_loading_start);
        boolean permissionCoarseLocation = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager
                .PERMISSION_GRANTED;
        boolean permissionFineLocation = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager
                .PERMISSION_GRANTED;

        if(!permissionCoarseLocation || !permissionFineLocation){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                setStringError(R.string.msg_error_no_permission_location);
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
                    setStringError(R.string.msg_error_no_permission_location);
                }
                break;
        }
    }

    public void tryAgain(View view){
        getDataFromServices();
    }

    private void setStringProgress(int stringResource){
        mTextLoadingProgress.setText(stringResource);
        mTextError.setText("");
        mButtonTryAgain.setVisibility(View.INVISIBLE);
    }

    private void setStringError(int stringResource){
        mTextLoadingProgress.setText("");
        mTextError.setText(stringResource);
        mButtonTryAgain.setVisibility(View.VISIBLE);
    }

    //  Splash.View  ///////////////////////////////////////////////////////////
    @Override
    public void showProgressGettingLocation() {
        setStringProgress(R.string.msg_loading_location);
    }

    @Override
    public void handleGettingLocationError(String error) {
        setStringError(R.string.msg_error_loading_location);
    }

    @Override
    public void onGettingLocationFinish(Location location) {
        mSplashPresenter.requestWeather(location);
    }

    @Override
    public void showProgressGettingWeather() {
        setStringProgress(R.string.msg_loading_weather);
    }

    @Override
    public void handleNoInternetConnectionError() {
        setStringError(R.string.msg_error_no_internet_connection);
    }

    @Override
    public void handleGettingWeatherError(String error) {
        setStringError(R.string.msg_error_loading_weather);
    }

    @Override
    public void navigateToMainScreen(String temperature, String status, String
                                     location) {
        startActivity(
                WeatherActivity.getIntent(this, temperature, status, location));
    }

}
