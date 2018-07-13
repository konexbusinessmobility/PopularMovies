package com.konexbusinessmobility.popularmovies.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private ImageView poster;
    private ImageView backdrop;
    private TextView movieTitle;
    private TextView description;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        backdrop = findViewById(R.id.movie_backdrop);
        movieTitle = findViewById(R.id.movie_title);
        description = findViewById(R.id.movie_description);
        poster = findViewById(R.id.movie_poster);

        mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        movieTitle.setText(mMovie.title);
        description.setText(mMovie.description);

        Picasso.get()
                .load(mMovie.posterPath)
                .error(R.drawable.not_found)
                .placeholder(R.drawable.searching)
                .into(poster);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View clickedPosition) {

                Intent detailIntent = new Intent(Intent.ACTION_VIEW);
                detailIntent.putExtra("position", (Parcelable) clickedPosition);
                startActivity(detailIntent);

            }
        });

    }
}