package com.konexbusinessmobility.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    private String description;
    private String voteAverage;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private String releaseDate;

    //  Movie object Constructor
    public Movie() {}

    //Gets the Movie title
    public String getTitle() {

        return title;

    }

    // Sets the Movie title
    public void setTitle(String title) {

        this.title = title;

    }

    // Get the path to the poster image
    public String getPosterPath() {

        return "http://image.tmdb.org/t/p/w780" + posterPath;

    }

    // Set the path to the poster image
    public void setPosterPath(String poster) {

        this.posterPath = poster;
    }

    // Get the Overview
    public String getOverview() {

        return overview;

    }

    // Set the overview
    public void setOverview(String overview) {

        this.overview = overview;

    }

    // Get the description
    public String getDescription() {

        return description;

    }

    // Set the description
    public void setDescription(String description) {

        this.description = description;

    }

    // Get the popularity of a movie
    public Double getPopularity() {
        return popularity;
    }

    // Set the popularity of a movie
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    // Get the backdrop image path
    public String getBackdropPath() {

        return "http://image.tmdb.org/t/p/w500" + backdropPath;

    }

    // Set the backdrop image path
    public void setBackdropPath(String backdrop) {

        this.backdropPath = backdrop;

    }

    @Override
    public int describeContents() {

        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(description);
        dest.writeString(voteAverage);
        dest.writeString(backdropPath);
        dest.writeString(releaseDate);

    }

    protected Movie(Parcel source) {

        title = source.readString();
        posterPath = source.readString();
        overview = source.readString();
        description = source.readString();
        voteAverage = source.readString();
        popularity = source.readDouble();
        backdropPath = source.readString();
        releaseDate = source.readString();

    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {

            return new Movie[size];

        }
    };

}