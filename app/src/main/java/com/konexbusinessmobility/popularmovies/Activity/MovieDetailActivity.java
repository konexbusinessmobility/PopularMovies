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
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView description;
    TextView overview;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.movie_title);
        description = findViewById(R.id.movie_description);
        poster = findViewById(R.id.movie_poster);
        overview = findViewById(R.id.overview);

       //Intent intent = getIntent();
       mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        title.setText(mMovie.getTitle());
        description.setText(mMovie.getDescription());

        Picasso.get()
                .load(mMovie.getPosterPath())
                .resize(getResources().getInteger(R.integer.tmdb_poster_w185_width),
                        getResources().getInteger(R.integer.tmdb_poster_w185_height))
                .error(R.drawable.not_found)
                .placeholder(R.drawable.searching)
                .into(poster);

        Picasso.get()
                .load(mMovie.getBackdropPath())
                .into(backdrop);

        String movieOverview = mMovie.getOverview();

        if (overview == null) {

            overview.setTypeface(null, Typeface.ITALIC);
            movieOverview = getResources().getString(R.string.no_summary_found);

        }

        overview.setText(movieOverview);

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