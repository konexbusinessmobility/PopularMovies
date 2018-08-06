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
import android.widget.ImageView;
import android.widget.TextView;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Activity.MovieDetailActivity;
import com.konexbusinessmobility.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> mMovies;
    private Context mContext;
    private final MovieAdapterOnClickHandler movieAdapterOnClickHandler;

    public interface MovieAdapterOnClickHandler{

        void onClick(Movie movie);

    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {


        this.movieAdapterOnClickHandler = clickHandler;

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView posterImage;
        private TextView mTitle;
        private TextView mDescription;


        public MovieViewHolder(View view) {

            super(view);
            posterImage = view.findViewById(R.id.movie_poster);
            mTitle = view.findViewById(R.id.movie_title);
            mDescription = view.findViewById(R.id.movie_description);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            movieAdapterOnClickHandler.onClick(mMovies.get(adapterPosition));


        }

    }

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
}