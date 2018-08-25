package com.konexbusinessmobility.popularmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Activity.MovieDetailActivity;
import com.konexbusinessmobility.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Movie[] mMovies;
    private final Context mContext;

    private final MovieAdapterOnClickListener mMovieAdapterOnClickListener;

    public interface MovieAdapterOnClickListener{

        void onItemClick(int clickedPosition);

    }

    public interface MovieAdapterOnClickHandler{

        void onClick(Movie movie);

    }

    public MovieAdapter(List<Movie> movies, MovieAdapterOnClickListener listener, Context context) {

        this.mMovies = movies;
        mMovieAdapterOnClickListener = listener;
        this.mContext = context;

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView posterImage;


        public MovieViewHolder(View view) {

            super(view);
            posterImage = view.findViewById(R.id.movie_poster);
            view.setOnClickListener(this);

        }

        private final RecyclerView.OnClickListener moviePosterClickListener = new RecyclerView.OnClickListener() {

            @Override
            public void onClick(AdapterView<?> parent, View v, int position) {

                Movie movie = (Movie) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicatonContext(), MovieDetailActivity.class);
                intent.putExtra(getResources().getString(R.string.parcel_movie), movie);

                startActivity(intent);
            }
    };

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            mContext = parent.getContext();
            int layoutIdForGridItem = R.layout.list_item_movie;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForGridItem, parent, shouldAttachToParentImmediately);
            return new MovieViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {

            setPostersOnline(movieViewHolder, position);

        }

        private void setPostersOnline(final MovieViewHolder movieViewHolder, final int position) {

            Log.v(TAG, "set movies called");
            String mMoviePosterPath = mMovies.get(position).getPosterPath();

            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w780".concat(mMoviePosterPath))
                    .into(movieViewHolder.posterImage);
        }

    @Override
    public int getItemCount() {

        if (null == mMovies) return 0;
        return mMovies.size();

    }

    public void setMoviePosters(List<Movie> movies) {

        mMovies = movies;
        notifyDataSetChanged();

    }

        @Override
        public void onClick(View v) {

        }
    }