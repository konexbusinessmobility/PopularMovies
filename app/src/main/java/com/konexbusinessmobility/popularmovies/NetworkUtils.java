package com.konexbusinessmobility.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.konexbusinessmobility.popularmovies.BuildConfig.MOVIE_API_KEY;

public class NetworkUtils {

    public final static String API_KEY = MOVIE_API_KEY;
    public final static String BASE_URL = "https://api.themoviedb.org/3/";

    public static boolean checkConnectivity(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());

    }

}
