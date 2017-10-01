package com.example.weathernow.models.localization;

import android.location.Location;
import android.os.Handler;

/**
 * Created by eduardo on 01/10/17.
 *
 * Localization.Service mock
 */

class LocalizationService implements Localization.Service {

    @Override
    public void requestLocation(final Localization.Callback callback) {
        //Mock
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onRequestLocationSuccess(new Location(""));
            }
        }, 3000);
    }
}
