package com.konexbusinessmobility.popularmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Activity.MovieDetailActivity;
import com.konexbusinessmobility.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private LayoutInflater mInflater;
    private List<Movie> movies;
    private final Context mContext;
    private final MovieAdapterOnClickListener movieAdapterOnClickListener;

    public MovieAdapter(List<Movie> movies, MovieAdapterOnClickListener listener, Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.movies = movies;
        movieAdapterOnClickListener = listener;

    }

    public interface MovieAdapterOnClickListener {

        void onItemClick(int clickedPosition);

    }

    public void setMovies(List<Movie> movies) {

        this.movies = new ArrayList<>();
        this.movies.addAll(movies);
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return (movies == null) ? 0 : movies.size();

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.movie_rows, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view, movieAdapterOnClickListener);


        return movieViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {

        Movie movie = movies.get(position);
        Picasso.get()
                .load(movie.getPosterPath())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView);

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imageView;
        final MovieAdapterOnClickListener movieAdapterOnClickListener;

        public MovieViewHolder(View view, MovieAdapterOnClickListener movieAdapterOnClickListener) {

            super(view);
            imageView = view.findViewById(R.id.movieRowImage);
            this.movieAdapterOnClickListener = movieAdapterOnClickListener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movies.get(clickedPosition));
            mContext.startActivity(intent);
            movieAdapterOnClickListener.onItemClick(clickedPosition);

        }
    }

}