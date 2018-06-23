package com.konexbusinessmobility.popularmovies.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";

    private Movie mMovie;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView description;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {

            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        } else {

            throw new IllegalArgumentException("Detail Activity did not receive a parcelable");
        }

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(mMovie.getTitle());

        backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.movie_title);
        description = findViewById(R.id.movie_description);
        poster = findViewById(R.id.movie_poster);

        title.setText(mMovie.getTitle());
        description.setText(mMovie.getDescription());
        Picasso.get()
                .load(mMovie.getPosterPath())
                .into(poster);
        Picasso.get()
                .load(mMovie.getBackdropPath())
                .into(backdrop);

    }
}