package com.example.secondphaseofapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secondphaseofapp.utils.LocationFactsUtils;
import com.example.secondphaseofapp.utils.ResumeUtils;

public class LocationFactsActivity extends AppCompatActivity {
    private final int BIRTH_PLACE_ID = 332123;
    private final int LIVE_NOW_ID = 116839;
    private final int LIKE_TO_LIVE_ID = 11388236;
    private final int SIGNIFICANT_OTHER_ID = 124918;

    private final String BIRTH_PLACE_URL = "https://en.wikipedia.org/wiki/Peterborough,_New_Hampshire";
    private final String LIVE_NOW_URL = "https://en.wikipedia.org/wiki/Dracut,_Massachusetts";
    private final String LIKE_TO_LIVE_URL = "https://en.wikipedia.org/wiki/Seattle";
    private final String SIGNIFICANT_OTHER_URL = "https://en.wikipedia.org/wiki/Salem,_New_Hampshire";

    private TextView birthPlaceSummary, liveNowSummary, likeToLiveSummary, significantOtherSummary;
    private TextView birthPlaceMore, liveNowMore, likeToLiveMore, significantOtherMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_facts);

        birthPlaceSummary = (TextView) findViewById(R.id.facts_birthplace_summary);
        liveNowSummary = (TextView) findViewById(R.id.facts_live_now_summary);
        likeToLiveSummary = (TextView) findViewById(R.id.facts_want_to_live_summary);
        significantOtherSummary = (TextView) findViewById(R.id.facts_bf_summary);

        loadSummary(BIRTH_PLACE_ID);
        loadSummary(LIVE_NOW_ID);
        loadSummary(LIKE_TO_LIVE_ID);
        loadSummary(SIGNIFICANT_OTHER_ID);


    }

    private void loadSummary(int id) {
        new GetSummaryTask().execute(id);
    }

    private void openWikipedia(String url) {
        Uri page = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, page);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.facts_birthplace_more:
                openWikipedia(BIRTH_PLACE_URL);
                break;
            case R.id.facts_live_now_more:
                openWikipedia(LIVE_NOW_URL);
                break;
            case R.id.facts_want_to_live_more:
                openWikipedia(LIKE_TO_LIVE_URL);
                break;
            case R.id.facts_bf_more:
                openWikipedia(SIGNIFICANT_OTHER_URL);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_location_facts, menu);
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
        else if (id == R.id.weather_menu_item) {
            Intent intent = new Intent(this, WeatherActivity.class);
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
                Log.d("MainActivity", "Resume failed to open.");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class GetSummaryTask extends AsyncTask<Integer, Void, String[]> {
        @Override
        protected String[] doInBackground(Integer... params) {
            int id = params[0];
            String jsonResponse = LocationFactsUtils.getSummaryJSON(id);
            String summary = LocationFactsUtils.parseSummary(jsonResponse, id);
            String[] result = {summary, String.valueOf(id)};
            return result;
        }

        @Override
        protected void onPostExecute(String[] result) {
            int id = Integer.valueOf(result[1]);
            String summary = result[0];
            switch (id) {
                case BIRTH_PLACE_ID:
                    birthPlaceSummary.setText(summary);
                    break;
                case LIVE_NOW_ID:
                    liveNowSummary.setText(summary);
                    break;
                case LIKE_TO_LIVE_ID:
                    likeToLiveSummary.setText(summary);
                    break;
                case SIGNIFICANT_OTHER_ID:
                    significantOtherSummary.setText(summary);
            }
        }
    }
}