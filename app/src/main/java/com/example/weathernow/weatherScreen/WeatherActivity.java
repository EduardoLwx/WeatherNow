package com.example.weathernow.weatherScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.weathernow.R;

public class WeatherActivity extends AppCompatActivity {
    private static final String EXTRA_TEMPERATURE = "com.example.weathernow" +
            ".weatherScreen.temperature";
    private static final String EXTRA_STATUS = "com.example.weathernow" +
            ".weatherScreen.status";
    private static final String EXTRA_LOCATION = "com.example.weathernow" +
            ".weatherScreen.location";

    public static Intent getIntent(Context context, String temperature, String
            status,
                                    String location){
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(EXTRA_TEMPERATURE, temperature);
        intent.putExtra(EXTRA_STATUS, status);
        intent.putExtra(EXTRA_LOCATION, location);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Intent intent = getIntent();
        ((TextView)findViewById(R.id.text_temperature))
                .setText(intent.getStringExtra(EXTRA_TEMPERATURE));

        ((TextView)findViewById(R.id.text_status))
                .setText(intent.getStringExtra(EXTRA_STATUS));

        ((TextView)findViewById(R.id.text_location))
                .setText(intent.getStringExtra(EXTRA_LOCATION));
    }
}
