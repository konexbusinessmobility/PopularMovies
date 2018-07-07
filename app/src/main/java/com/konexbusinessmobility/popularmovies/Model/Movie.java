package com.konexbusinessmobility.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie{

    @SerializedName("title")
    private String mOriginalTitle;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("overview")
    private String movieOverview;
    private String movieDescription;
    @SerializedName("popularity")
    private Double moviePopularity;
    @SerializedName("backdrop_path")
    private String movieBackdropPath;

    //  Movie object Constructor
    public Movie(String originalTitle, String posterPath, String overview, String description, String backdropPath) {

        mOriginalTitle = originalTitle;
        mPosterPath = posterPath;
        movieOverview = overview;
        movieDescription = description;
        movieBackdropPath = backdropPath;

    }
    //Gets the Movie title
    public String getTitle() {

        return mOriginalTitle;

    }

    // Sets the Movie title
    public void setTitle(String originalTitle) {

        mOriginalTitle = originalTitle;

    }

    // Get the path to the poster image
    public String getPosterPath() {

        final String TMDB_POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";

        return TMDB_POSTER_BASE_URL + mPosterPath;

    }

    // Set the path to the poster image
    public void setPosterPath(String poster) {

        mPosterPath = poster;
    }

    // Get the Overview
    public String getOverview() {

        return movieOverview;

    }

    // Set the overview
    public void setOverview(String overview) {

        movieOverview = overview;

    }

    // Get the description
    public String getDescription() {

        return movieDescription;

    }

    // Set the description
    public void setDescription(String description) {

        movieDescription = description;

    }

    // Get the popularity of a movie
    public Double getPopularity() {
        return moviePopularity;
    }

    // Set the popularity of a movie
    public void setPopularity(Double popularity) {
        moviePopularity = popularity;
    }

    // Get the backdrop image path
    public String getBackdropPath() {

        return "http://image.tmdb.org/t/p/w185" + movieBackdropPath;

    }

    // Set the backdrop image path
    public void setBackdropPath(String backdrop) {

        movieBackdropPath = backdrop;

    }

    /*@Override
    public int describeContents() {

        return 0;

    }*/

    /*@Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mOriginalTitle);
        dest.writeString(mPosterPath);
        dest.writeString(movieOverview);
        dest.writeString(movieDescription);
        dest.writeString(movieBackdropPath);

    }

    protected Movie() {

        mOriginalTitle = source.readString();
        mPosterPath = source.readString();
        movieOverview = source.readString();
        movieDescription = source.readString();
        moviePopularity = source.readDouble();
        movieBackdropPath = source.readString();

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
    };*/

}