package com.konexbusinessmobility.popularmovies.Rest;


import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    @GET("/movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key);
}
