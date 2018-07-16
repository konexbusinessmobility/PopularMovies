package com.konexbusinessmobility.popularmovies.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Adapter.MovieAdapter;
import com.konexbusinessmobility.popularmovies.NetworkUtils;
import com.konexbusinessmobility.popularmovies.R;
import com.konexbusinessmobility.popularmovies.Rest.MovieAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;


//  Reference: http://www.chansek.com/RecyclerView-no-adapter-attached-skipping-layout/

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private List<Movie> movies;
    private MovieAPI movieAPI;

    //private MovieAPI movieAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        // Set RecyclerView and the GridLayout Manager 2 columns
        mRecyclerView = findViewById(R.id.contentMainRecyclerView);
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieAPI = retrofit.create(MovieAPI.class);

        movieAPI.getMovies(NetworkUtils.API_KEY).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                movies = response.body();
                mAdapter = new MovieAdapter(movies);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Could not get Movies!", Toast.LENGTH_SHORT).show();

            }
        });


    }

}


