package com.example.secondphaseofapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.secondphaseofapp.utils.WeatherUtils;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public static class GetWeatherTask extends AsyncTask<Double, Void, String[]> {
        @Override
        protected String[] doInBackground(Double... params) {
            double lat = params[0];
            double lon = params[1];
            String jsonResponse = WeatherUtils.getWeatherData(lat, lon);
            return WeatherUtils.parseWeatherData(jsonResponse);
        }
    }
}