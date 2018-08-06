/*
package com.konexbusinessmobility.popularmovies.Rest;

import android.content.Context;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.utilities.NetworkUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(Context mContext) throws SecurityException{


        if (!NetworkUtils.checkConnectivity(mContext)) {

            throw new SecurityException("No Internet Connection");

        }

        if (retrofit==null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkUtils.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

         }

        return retrofit;

    }

}*/
