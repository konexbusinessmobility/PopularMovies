package com.konexbusinessmobility.popularmovies.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.konexbusinessmobility.popularmovies.Model.Movie;
import com.konexbusinessmobility.popularmovies.Activity.MovieDetailActivity;
import com.konexbusinessmobility.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mMovies;

    public MovieAdapter(List<Movie> movies) {

        this.mMovies = movies;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);


        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {

        Movie movie = mMovies.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {

        return mMovies.size();

    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView title;
        private TextView description;


        public MovieViewHolder(View view) {

            super(view);
            poster = view.findViewById(R.id.movie_poster);
            title = view.findViewById(R.id.movie_title);
            description = view.findViewById(R.id.movie_description);

        }

        public void bind(Movie movie) {

            title.setText(movie.title);
            description.setText(movie.description);
            Picasso.get()
                    .load(movie.posterPath)
                    .into(poster);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int clickedPosition = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, mMovies.get(clickedPosition));
                    itemView.getContext().startActivity(intent);

                }

            });

        }

    }
}