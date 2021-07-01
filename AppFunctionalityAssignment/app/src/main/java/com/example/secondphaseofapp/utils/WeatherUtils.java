package com.example.secondphaseofapp.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.secondphaseofapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class WeatherUtils {

    private static final String LOG_TAG = "WeatherUtils";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/onecall";
    private static final String API_KEY = "dcfe1b125732db6052aa55b7b8909bad";

    private static final String LAT_PARAM = "lat";
    private static final String LON_PARAM = "lon";
    private static final String UNITS_PARAM = "units";
    private static final String KEY_PARAM = "appid";
    private static final String EXCLUDE_PARAM = "exclude";

    private static final String UNITS_VAL = "imperial";
    private static final String EXCLUDE_VAL = "minutely,hourly,alerts";

    /* Takes a latitude and longitude and returns a JSON response.
        Adapted from Udacity Sunshine project.*/
    public static String getWeatherData(double lat, double lon) {
        Uri weatherQuery = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(LAT_PARAM, String.valueOf(lat))
                .appendQueryParameter(LON_PARAM, String.valueOf(lon))
                .appendQueryParameter(UNITS_PARAM, UNITS_VAL)
                .appendQueryParameter(EXCLUDE_PARAM, EXCLUDE_VAL)
                .appendQueryParameter(KEY_PARAM, API_KEY).build();
        try {
            URL weatherQueryUrl = new URL(weatherQuery.toString());
            HttpsURLConnection connection = (HttpsURLConnection) weatherQueryUrl.openConnection();
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

    /* Parses JSON response and returns relevant weather data in an array. */
    public static String[] parseWeatherData(String response) {
        String weatherCode, highTemp, lowTemp;
        try {
            JSONObject weatherDataJson = new JSONObject(response);
            int currentWeatherId = weatherDataJson.getJSONObject("current")
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getInt("id");
            JSONObject tempJson = weatherDataJson.getJSONArray("daily")
                    .getJSONObject(0)
                    .getJSONObject("temp");
            double highTempRaw = tempJson.getDouble("max");
            double lowTempRaw = tempJson.getDouble("min");
            weatherCode = String.valueOf(currentWeatherId);
            highTemp = String.valueOf(highTempRaw);
            lowTemp = String.valueOf(lowTempRaw);
            String[] result = {weatherCode, highTemp, lowTemp};
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* Retrieves weather description based on weather ID. Source: Udacity Sunshine Project
        Link: https://github.com/udacity/ud851-Sunshine/blob/dbd998b89b78afac6c744ad92f14c7ba5eaf7531/S02.01-Solution-Networking/app/src/main/java/com/example/android/sunshine/utilities/SunshineWeatherUtils.java#L145
     */
    public static String getStringForWeatherCondition(Context context, int weatherId) {
        int stringId;
        if (weatherId >= 200 && weatherId <= 232) {
            stringId = R.string.condition_2xx;
        } else if (weatherId >= 300 && weatherId <= 321) {
            stringId = R.string.condition_3xx;
        } else switch (weatherId) {
            case 500:
                stringId = R.string.condition_500;
                break;
            case 501:
                stringId = R.string.condition_501;
                break;
            case 502:
                stringId = R.string.condition_502;
                break;
            case 503:
                stringId = R.string.condition_503;
                break;
            case 504:
                stringId = R.string.condition_504;
                break;
            case 511:
                stringId = R.string.condition_511;
                break;
            case 520:
                stringId = R.string.condition_520;
                break;
            case 531:
                stringId = R.string.condition_531;
                break;
            case 600:
                stringId = R.string.condition_600;
                break;
            case 601:
                stringId = R.string.condition_601;
                break;
            case 602:
                stringId = R.string.condition_602;
                break;
            case 611:
                stringId = R.string.condition_611;
                break;
            case 612:
                stringId = R.string.condition_612;
                break;
            case 615:
                stringId = R.string.condition_615;
                break;
            case 616:
                stringId = R.string.condition_616;
                break;
            case 620:
                stringId = R.string.condition_620;
                break;
            case 621:
                stringId = R.string.condition_621;
                break;
            case 622:
                stringId = R.string.condition_622;
                break;
            case 701:
                stringId = R.string.condition_701;
                break;
            case 711:
                stringId = R.string.condition_711;
                break;
            case 721:
                stringId = R.string.condition_721;
                break;
            case 731:
                stringId = R.string.condition_731;
                break;
            case 741:
                stringId = R.string.condition_741;
                break;
            case 751:
                stringId = R.string.condition_751;
                break;
            case 761:
                stringId = R.string.condition_761;
                break;
            case 762:
                stringId = R.string.condition_762;
                break;
            case 771:
                stringId = R.string.condition_771;
                break;
            case 781:
                stringId = R.string.condition_781;
                break;
            case 800:
                stringId = R.string.condition_800;
                break;
            case 801:
                stringId = R.string.condition_801;
                break;
            case 802:
                stringId = R.string.condition_802;
                break;
            case 803:
                stringId = R.string.condition_803;
                break;
            case 804:
                stringId = R.string.condition_804;
                break;
            case 900:
                stringId = R.string.condition_900;
                break;
            case 901:
                stringId = R.string.condition_901;
                break;
            case 902:
                stringId = R.string.condition_902;
                break;
            case 903:
                stringId = R.string.condition_903;
                break;
            case 904:
                stringId = R.string.condition_904;
                break;
            case 905:
                stringId = R.string.condition_905;
                break;
            case 906:
                stringId = R.string.condition_906;
                break;
            case 951:
                stringId = R.string.condition_951;
                break;
            case 952:
                stringId = R.string.condition_952;
                break;
            case 953:
                stringId = R.string.condition_953;
                break;
            case 954:
                stringId = R.string.condition_954;
                break;
            case 955:
                stringId = R.string.condition_955;
                break;
            case 956:
                stringId = R.string.condition_956;
                break;
            case 957:
                stringId = R.string.condition_957;
                break;
            case 958:
                stringId = R.string.condition_958;
                break;
            case 959:
                stringId = R.string.condition_959;
                break;
            case 960:
                stringId = R.string.condition_960;
                break;
            case 961:
                stringId = R.string.condition_961;
                break;
            case 962:
                stringId = R.string.condition_962;
                break;
            default:
                return context.getString(R.string.condition_unknown, weatherId);
        }
        return context.getString(stringId);
    }

    /* Retrieves weather icon resource based on weather ID. Source: Udacity Sunshine Project
        Link: https://github.com/udacity/ud851-Sunshine/blob/dbd998b89b78afac6c744ad92f14c7ba5eaf7531/S02.01-Solution-Networking/app/src/main/java/com/example/android/sunshine/utilities/SunshineWeatherUtils.java#L361
     */
    public static int getArtResourceForWeatherCondition(int weatherId) {
        /*
         * Based on weather code data found at:
         * http://bugs.openweathermap.org/projects/api/wiki/Weather_Condition_Codes
         */
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.art_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.art_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.art_clear;
        }
        Log.e(LOG_TAG, "Unknown Weather: " + weatherId);
        return R.drawable.art_storm;
    }
}
