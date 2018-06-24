package com.konexbusinessmobility.popularmovies.Activity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Adapter.MovieAdapter;
import com.konexbusinessmobility.popularmovies.Model.MovieResponse;
import com.konexbusinessmobility.popularmovies.NetworkUtils;
import com.konexbusinessmobility.popularmovies.R;
import com.konexbusinessmobility.popularmovies.Rest.MovieAPI;
import com.konexbusinessmobility.popularmovies.Rest.MovieClient;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.konexbusinessmobility.popularmovies.Rest.MovieClient.getRetrofit;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickListener {
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private List<Movie> movies;
    private final Context mContext = MainActivity.this;
    private MovieAPI movieAPI;
    private TextView showMessage;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        showMessage = findViewById(R.id.show_message);

        mRecyclerView = findViewById(R.id.contentMainRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);


            getPopularMovies();

        }


    @Override
    public void onItemClick(int clickedPosition) {

        Intent detailIntent = new Intent(this, MovieDetailActivity.class);
        detailIntent.putExtra("position", clickedPosition);
        startActivity(detailIntent);
    }

        private void getPopularMovies() {

        if (TextUtils.isEmpty(NetworkUtils.API_KEY)) {

            showErrorMessage("Obtain a API_KEY from Themoviedb.org");
            return;

        } try {

            movieAPI = MovieClient.getRetrofit(mContext).create(MovieAPI.class);

            Call<MovieResponse> call = movieAPI.getMovies(NetworkUtils.API_KEY);
            call.enqueue(new Callback<MovieResponse>() {

                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                    int statusCode = response.code();
                    if (response.isSuccessful()) {
                        movies = response.body().getResults();
                        Log.d(TAG, "Number of Movies Received: " + movies.size());
                        if (mAdapter == null) {
                            mAdapter = new MovieAdapter(movies, MainActivity.this, mContext);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setHasFixedSize(true);

                        } else {

                            mAdapter.setMovies(movies);
                            mAdapter.notifyDataSetChanged();
                        }

                    } else {

                        showErrorMessage("Failed to load list of " + " Status Code=" + statusCode);

                        }

                    }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {

                    Log.e(TAG, "HTTP Call Failed " + t.getMessage());
                    showErrorMessage("HTTP Call got failed " + t.getMessage());

                }
            });

            } catch (Exception e) {

            Log.e(TAG, "No Internet Connection " + e.getMessage());
            showErrorMessage("No Internet Connection");

            }

        }

        private void showErrorMessage(String msg) {

        mRecyclerView.setVisibility(View.INVISIBLE);
        showMessage.setText(msg);
        showMessage.setVisibility(View.VISIBLE);

        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;

        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){

            int menuID = item.getItemId();

            if (menuID == R.id.action_settings) {

                return true;
            }

            return super.onOptionsItemSelected(item);


        }

    }


