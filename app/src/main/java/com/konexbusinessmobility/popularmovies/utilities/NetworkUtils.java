package com.konexbusinessmobility.popularmovies.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.konexbusinessmobility.popularmovies.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.konexbusinessmobility.popularmovies.BuildConfig.MOVIE_API_KEY;

public class NetworkUtils {

    public final static String API_KEY = "api_key";
    public final static String BASE_URL = "https://api.themoviedb.org/";
    public static final String API_VERSION = "3";
    public static final String KEY = BuildConfig.MOVIE_API_KEY;

    public static final String TAG = NetworkUtils.class.getSimpleName();

    public static URL buildURL(String mediaType, String filter) {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(API_VERSION)
                .appendPath(mediaType)
                .appendPath(filter)
                .appendQueryParameter(API_KEY, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /*public NetworkUtils() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(mediaType, "{}");
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/popular?page=1&language=en-US&api_key=%3C%3Capi_key%3E%3E")
                .get()
                .build();

        Response response = client.newCall(request).execute();

    }*/


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url the URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading.
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}