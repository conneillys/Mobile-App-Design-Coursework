package com.example.secondphaseofapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secondphaseofapp.utils.ResumeUtils;
import com.example.secondphaseofapp.utils.WeatherUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class WeatherActivity extends AppCompatActivity {
    private final String TAG = "WeatherActivity";

    // Weather views
    private ImageView currentWeatherIcon, myTownWeatherIcon, likeToBeIcon, familyIcon;
    private TextView currentWeatherDesc, myTownWeatherDesc, likeToBeWeatherDesc, familyWeatherDesc;
    private TextView currentWeatherHigh, myTownWeatherHigh, likeToBeWeatherHigh, familyWeatherHigh;
    private TextView currentWeatherLow, myTownWeatherLow, likeToBeWeatherLow, familyWeatherLow;

    // Static location coordinates
    private final double myTownLat = 42.6704, myTownLon = -71.3020;
    private final double likeToBeLat = 41.8459, likeToBeLon = -70.9495;
    private final double familyLat = 42.6659, familyLon = -71.5884;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Set weather icon views
        currentWeatherIcon = findViewById(R.id.current_weather_icon);
        myTownWeatherIcon = findViewById(R.id.my_town_weather_icon);
        likeToBeIcon = findViewById(R.id.desired_loc_weather_icon);
        familyIcon = findViewById(R.id.family_loc_weather_icon);

        // Set description views
        currentWeatherDesc = findViewById(R.id.current_loc_weather_desc);
        myTownWeatherDesc = findViewById(R.id.my_town_weather_desc);
        likeToBeWeatherDesc = findViewById(R.id.desired_loc_weather_desc);
        familyWeatherDesc = findViewById(R.id.family_loc_weather_desc);

        // Set high temp views
        currentWeatherHigh = findViewById(R.id.current_loc_high_temp);
        myTownWeatherHigh = findViewById(R.id.my_town_high_temp);
        likeToBeWeatherHigh = findViewById(R.id.desired_loc_high_temp);
        familyWeatherHigh = findViewById(R.id.family_loc_high_temp);

        // Set low temp views
        currentWeatherLow = findViewById(R.id.current_loc_low_temp);
        myTownWeatherLow = findViewById(R.id.my_town_low_temp);
        likeToBeWeatherLow = findViewById(R.id.desired_loc_low_temp);
        familyWeatherLow = findViewById(R.id.family_loc_low_temp);

        // Get most recent location recorded and populate the elements for current weather
        FusedLocationProviderClient locClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 40);
        }
        locClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    loadWeatherData(location.getLatitude(), location.getLongitude(), 0);
                }
            }
        });

        // Load weather data for everything else
        loadWeatherData(myTownLat, myTownLon, 1);
        loadWeatherData(likeToBeLat, likeToBeLon, 2);
        loadWeatherData(familyLat, familyLon, 3);
    }

    /* Handles loading weather data with the AsyncTask. Index included to fill in proper weather
        fields.
     */
    private void loadWeatherData(double lat, double lon, double index) {
        new GetWeatherTask().execute(lat, lon, index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_menu_item) {
            Intent intent = new Intent(this, MainActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else if (id == R.id.places_facts_menu_item) {
            Intent intent = new Intent(this, LocationFactsActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else if (id == R.id.portfolio_menu_item) {
            Intent intent = new Intent(this, PortfolioActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else if (id == R.id.resume_menu_item) {
            Intent intent = new Intent(Intent.ACTION_VIEW, ResumeUtils.getResumeUri(this));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            else {
                Log.d("WeatherActivity", "Resume failed to open.");
            }
        }
        else if (id == R.id.bonus_facts_menu_item) {
            Intent intent = new Intent(this, BonusFactsActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        FusedLocationProviderClient locClient = LocationServices.getFusedLocationProviderClient(this);
        locClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    loadWeatherData(location.getLatitude(), location.getLongitude(), 0);
                }
            }
        });
    }

    public class GetWeatherTask extends AsyncTask<Double, Void, String[]> {
        @Override
        protected String[] doInBackground(Double... params) {
            double lat = params[0];
            double lon = params[1];
            double indexArg = params[2];
            int index = (int) indexArg;
            String jsonResponse = WeatherUtils.getWeatherData(lat, lon);
            Log.d(TAG, jsonResponse);
            String[] weatherData = WeatherUtils.parseWeatherData(jsonResponse);
            String[] result = new String[4];
            for (int i = 0; i < 3; i++) {
                result[i] = weatherData[i];
            }
            result[3] = String.valueOf(index);
            return result;
        }

        @Override
        protected void onPostExecute(String[] data) {
            if (data != null) {
                int i = Integer.valueOf(data[3]);
                int code = Integer.valueOf(data[0]);
                String high = data[1];
                String low = data[2];
                switch (i) {
                    case 0:  // Current location
                        currentWeatherIcon.setImageResource(WeatherUtils.getArtResourceForWeatherCondition(code));
                        currentWeatherDesc.setText(WeatherUtils.getStringForWeatherCondition(WeatherActivity.this, code));
                        currentWeatherHigh.setText(high + "°");
                        currentWeatherLow.setText(low + "°");
                        break;
                    case 1:  // My town
                        myTownWeatherIcon.setImageResource(WeatherUtils.getArtResourceForWeatherCondition(code));
                        myTownWeatherDesc.setText(WeatherUtils.getStringForWeatherCondition(WeatherActivity.this, code));
                        myTownWeatherHigh.setText(high + "°");
                        myTownWeatherLow.setText(low + "°");
                        break;
                    case 2:  // Desired location
                        likeToBeIcon.setImageResource(WeatherUtils.getArtResourceForWeatherCondition(code));
                        likeToBeWeatherDesc.setText(WeatherUtils.getStringForWeatherCondition(WeatherActivity.this, code));
                        likeToBeWeatherHigh.setText(high + "°");
                        likeToBeWeatherLow.setText(low + "°");
                        break;
                    case 3:  // Family
                        familyIcon.setImageResource(WeatherUtils.getArtResourceForWeatherCondition(code));
                        familyWeatherDesc.setText(WeatherUtils.getStringForWeatherCondition(WeatherActivity.this, code));
                        familyWeatherHigh.setText(high + "°");
                        familyWeatherLow.setText(low + "°");
                }
            }
        }
    }
}