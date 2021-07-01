package com.example.secondphaseofapp.utils;

import android.net.Uri;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class LocationFactsUtils {

    private static final String BASE_URL = "https://en.wikipedia.org/w/api.php";

    private static final String FORMAT_PARAM = "format";
    private static final String ACTION_PARAM = "action";
    private static final String PROP_PARAM = "prop";
    private static final String EXINTRO_PARAM = "exintro";
    private static final String EXPLAINTEXT_PARAM = "explaintext";
    private static final String REDIRECTS_PARAM = "redirects";
    private static final String ID_PARAM = "pageids";

    private static final String FORMAT_VAL = "json";
    private static final String ACTION_VAL = "query";
    private static final String PROP_VAL = "extracts";
    private static final String REDIRECTS_VAL = "1";

    // Gets summary text JSON from Wikipedia page, given the page's ID.
    public static String getSummaryJSON(int pageId) {
        Uri wikiQuery = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(FORMAT_PARAM, FORMAT_VAL)
                .appendQueryParameter(ACTION_PARAM, ACTION_VAL)
                .appendQueryParameter(PROP_PARAM, PROP_VAL)
                .appendQueryParameter(EXINTRO_PARAM, null)
                .appendQueryParameter(EXPLAINTEXT_PARAM, null)
                .appendQueryParameter(REDIRECTS_PARAM, REDIRECTS_VAL)
                .appendQueryParameter(ID_PARAM, String.valueOf(pageId)).build();
        try {
            URL wikiQueryUrl = new URL(wikiQuery.toString());
            HttpsURLConnection connection = (HttpsURLConnection) wikiQueryUrl.openConnection();
            InputStream in = connection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");  // Stores entire response in one token
            String response = null;
            if (scanner.hasNext()) {
                response = scanner.next();
            }
            scanner.close();
            connection.disconnect();
            return response;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieves summary from response JSON.
    public static String parseSummary(String response, int pageId) {
        try {
            JSONObject summaryJson = new JSONObject(response);
            return summaryJson.getJSONObject("query")
                    .getJSONObject("pages")
                    .getJSONObject(String.valueOf(pageId))
                    .getString("extract");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
