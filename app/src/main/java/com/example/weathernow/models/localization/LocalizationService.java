package com.example.weathernow.models.localization;

import android.app.Application;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by eduardo on 01/10/17.
 *
 * Localization.Service mock
 */

class LocalizationService implements Localization.Service {

    private Application mApplication;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    LocalizationService(Application application){
        mApplication = application;
        mFusedLocationProviderClient = LocationServices
                .getFusedLocationProviderClient(mApplication);
    }

    @Override
    @SuppressWarnings("MissingPermission")
    public void requestLocation(final Localization.Callback callback) {
        mFusedLocationProviderClient.getLastLocation()
            .addOnSuccessListener(
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            callback.onRequestLocationSuccess(location);
                        }
                    }
                })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    callback.onRequestLocationFail(e.toString());
                }
            });
    }
}
