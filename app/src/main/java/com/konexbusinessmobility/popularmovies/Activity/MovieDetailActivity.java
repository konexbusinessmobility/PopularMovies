package com.konexbusinessmobility.popularmovies.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String TAG = MovieDetailActivity.class.getSimpleName();

    private Movie mMovie;
    private ImageView mPoster;
    private ImageView mBackdrop;
    private TextView mMovieTitle;
    private TextView mDescription;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_details);

        mBackdrop = findViewById(R.id.movie_backdrop);
        mMovieTitle = findViewById(R.id.movie_title);
        mDescription = findViewById(R.id.movie_description);
        mPoster = findViewById(R.id.movie_poster);

        Intent intent = getIntent();
        if (intent != null) {
            intent.hasExtra("movie");
            mMovie = (Movie) getIntent().getExtras().getSerializable("movie");
            mMovieTitle.setText(mMovie.mTitle);
            mDescription.setText(mMovie.mDescription);

            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w1280".concat(mMovie.getPosterPath()))
                    .error(R.drawable.not_found)
                    .placeholder(R.drawable.searching)
                    .into(mPoster);

            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500".concat(mMovie.getBackdropPath()))
                    .into(mBackdrop);

            mMovieTitle.setText(mMovie.getTitle());
            mDescription.setText(mMovie.getDescription());



        }
    }
}