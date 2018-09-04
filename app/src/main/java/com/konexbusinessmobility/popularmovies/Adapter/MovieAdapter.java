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
import java.util.LinkedList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = MovieAdapter.class.getSimpleName();
    private static final String POSTER_PATH_780 = "http://image.tmdb.org/t/p/w780/";
    private List<Movie> mMovies;
    private final MovieAdapterOnClickListener mMovieAdapterOnClickListener;


    public void setMovies(List<Movie> movies) {
        this.mMovies = movies;
        this.notifyDataSetChanged();
    }

    public interface MovieAdapterOnClickListener {
        void onClick(Movie movie);
    }

    public MovieAdapter(MovieAdapterOnClickListener listener) {
        mMovieAdapterOnClickListener = listener;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForGridItem = R.layout.list_item_movie;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForGridItem, parent, shouldAttachToParentImmediately);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {

        Log.v(TAG, "set movies called");
        String mMoviePosterPath = mMovies.get(position).getPosterPath();

        Picasso.get()
                .load(POSTER_PATH_780 + mMoviePosterPath)
                .into(movieViewHolder.posterImage);
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void setMoviePosters(List<Movie> movies) {

        mMovies = movies;
        notifyDataSetChanged();

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView posterImage;


        public MovieViewHolder(View view) {
            super(view);
            posterImage = view.findViewById(R.id.movie_poster_list_item);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Movie movie = mMovies.get(getAdapterPosition());
            mMovieAdapterOnClickListener.onClick(movie);
        }
    }
}