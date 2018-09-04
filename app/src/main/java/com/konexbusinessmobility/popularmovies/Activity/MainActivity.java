

package com.konexbusinessmobility.popularmovies.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Adapter.MovieAdapter;
import com.konexbusinessmobility.popularmovies.utilities.MoviesJsonUtils;
import com.konexbusinessmobility.popularmovies.utilities.NetworkUtils;
import com.konexbusinessmobility.popularmovies.R;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//  Reference: https://medium.com/@sotti/android-networking-ii-okhttp-retrofit-moshi-and-picasso-c381f6c0efd8
//  Reference: https://github.com/drberg/Popular-Movies-Stage-1/blob/master/app/src/main/java/com/ringkjob/popularmovies/MainActivity.java
//  Reference: https://github.com/drberg/Popular-Movies-Stage-1/blob/master/app/src/main/java/com/ringkjob/popularmovies/MainActivity.java
//  Reference: https://developers.themoviedb.org/3/movies/get-popular-movies
//  Reference: https://github.com/hennasingh/PopularMovies_Project2/tree/master/app/src/main/java/com/artist/web/popularmovies
//  Reference: http://mateoj.com/2015/10/06/creating-movies-app-retrofit-picasso-android/
//  Reference: http://mateoj.com/2015/10/07/creating-movies-app-retrofit-picass-android-part2/
//  Reference: http://www.chansek.com/RecyclerView-no-adapter-attached-skipping-layout/
//  Reference: https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit
//  Reference: https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit
//  Reference: https://github.com/codepath/android_guides/wiki/Consuming-APIs-with-Retrofit#automated-approach---auto-generating-the-java-classes
//  Reference: http://www.chansek.com/RecyclerView-no-adapter-attached-skipping-layout/
//  Reference: https://www.androidhive.info/2016/01/android-working-with-recycler-view/
//  Reference: https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/?utm_source=recyclerview&utm_medium=site&utm_campaign=refer_article
//  Reference: https://developer.android.com/training/appbar/actions
//  Reference: https://www.youtube.com/watch?v=A5xcjzBC-sw


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private Menu mMenu;
    private MovieAdapter mAdapter;
    private TextView mErrorMessageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        // Set RecyclerView and the GridLayout Manager 2 columns
        mRecyclerView = findViewById(R.id.contentMainRecyclerView);
        mErrorMessageDisplay = findViewById(R.id.movie_error_message_display);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(new MovieAdapter.MovieAdapterOnClickListener() {
            @Override
            public void onClick(Movie movie) {
                Log.v(TAG, "action on main activity");
                Intent movieDetailIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
                movieDetailIntent.putExtra("movie", movie);
                startActivity(movieDetailIntent);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        loadMovieData("movie", "popular");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu, menu);

        /*menu.add(Menu.NONE, R.string.pref_sort_pop_desc_key, Menu.NONE, null)
                .setVisible(false)
                .setIcon(R.drawable.ic_action_whatshot)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add(Menu.NONE, R.string.pref_sort_vote_avg_desc_key, Menu.NONE, null)
                .setVisible(false)
                .setIcon(R.drawable.ic_action_poll)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.string.pref_sort_pop_desc_key:
                updateSharedPreferences(getString(R.string.tmdb_sort_pop_desc));
                getMoviesFromTMDB(getSortMethod());
                updateMenu();
                return true;

            case R.string.pref_sort_vote_avg_desc_key:
                updateSharedPreferences(getString(R.string.tmdb_sort_vote_avg_desc));
                getMoviesFromTMDB(getSortMethod());
                updateMenu();
                return true;

                default:
        }

        return super.onOptionsItemSelected(item);

    }

    private void showMovieData() {

        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    private void showError() {

        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);

    }

    private String getSortMethod() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        return preferences.getString(getString(R.string.pref_sort_method_key),
                getString(R.string.tmdb_sort_pop_desc));

    }

    private void loadMovieData(String media, String filter) {

        new FetchMovieTask().execute(media, filter);

    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void getMoviesFromTMDB(String sortMethod) {
        if (isNetworkAvailable()) {
            new FetchMovieTask().execute(sortMethod);
        } else {
            Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
        }
    }

    private void updateMenu() {

        String sortMethod = getSortMethod();

        if (sortMethod.equals(getString(R.string.tmdb_sort_pop_desc))) {
            mMenu.findItem(R.string.pref_sort_pop_desc_key).setVisible(false);
            mMenu.findItem(R.string.pref_sort_vote_avg_desc_key).setVisible(true);
        } else {
            mMenu.findItem(R.string.pref_sort_vote_avg_desc_key).setVisible(false);
            mMenu.findItem(R.string.pref_sort_pop_desc_key).setVisible(true);
        }
    }

    private void updateSharedPreferences(String sortMethod) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_sort_method_key), sortMethod);
        editor.apply();
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
                mAdapter.setMovies(movieData);
            } else {
                showError();
            }
        }
    }
}


