package com.example.weathernow.models.localization;

import android.location.Location;

/**
 * Created by eduardo on 01/10/17.
 *
 * Contract for Localization.Service and Localization.Callback
 */

public interface Localization {

    interface Service {
        void requestLocation(Callback callback);
    }

    interface Callback {
        void onRequestLocationSuccess(Location location);

        void onRequestLocationFail(String Error);
    }

}
