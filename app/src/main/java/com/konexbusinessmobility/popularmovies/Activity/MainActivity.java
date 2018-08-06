package com.konexbusinessmobility.popularmovies.Activity;

import android.content.Intent;
import android.net.Network;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Adapter.MovieAdapter;
import com.konexbusinessmobility.popularmovies.utilities.MoviesJsonUtils;
import com.konexbusinessmobility.popularmovies.utilities.NetworkUtils;
import com.konexbusinessmobility.popularmovies.R;
import com.konexbusinessmobility.popularmovies.Rest.MovieAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//  Reference: http://www.chansek.com/RecyclerView-no-adapter-attached-skipping-layout/

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private TextView mErrorMessageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set RecyclerView and the GridLayout Manager 2 columns
        mRecyclerView = findViewById(R.id.contentMainRecyclerView);
        mErrorMessageDisplay = findViewById(R.id.movie_error_message_display);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        loadMovieData("movie", "popular");

    }
    private void loadMovieData(String media, String filter) {

        new FetchMovieTask().execute(media, filter);

    }

    public void onClick(Movie movie) {

        Log.v(TAG, "action on main activity");
        Intent movieDetailIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
        movieDetailIntent.putExtra("movie", movie);
        startActivity(movieDetailIntent);

        }

        private void showMovieData() {

        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

        }

        private void showError() {

        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);

        }

        public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

            @Override
            protected List<Movie> doInBackground(String... strings) {
                if (strings.length == 0) {
                return null;
            }

                String endpoint = strings[0];
                String contentType = strings[1];
                URL moviesRequestUrl = NetworkUtils.buildURL(endpoint, contentType);

                try {

                    String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
                    List<Movie> movieJsonData = MoviesJsonUtils.getMoviesStringsFromJson(MainActivity.this, jsonMovieResponse);
                    return movieJsonData;

                } catch (Exception e) {

                    e.printStackTrace();
                    return Collections.emptyList();

                }

        }

        @Override
            protected void onPostExecute(List<Movie> movieData) {

                if (movieData != null) {

                    showMovieData();
                    mAdapter.setMoviePosters(movieData);

                } else {

                    showError();
                }
        }

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieAPI = retrofit.create(MovieAPI.class);

        movies = new ArrayList<Movie>();



        movieAPI.getMovies(NetworkUtils.API_KEY).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                 movies.addAll();


            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Could not get Movies!", Toast.LENGTH_SHORT).show();

            }
        });*/


    }

}


