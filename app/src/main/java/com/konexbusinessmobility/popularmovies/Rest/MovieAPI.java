package com.konexbusinessmobility.popularmovies.Rest;


import com.konexbusinessmobility.popularmovies.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("movie/popular")
    Call<List<Movie>> getMovies(@Query("api_key") String API_KEY);

}
