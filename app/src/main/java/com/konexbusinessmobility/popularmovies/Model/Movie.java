package com.konexbusinessmobility.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Movie implements Parcelable {

    public String title;
    public String posterPath;
    public String description;
    private String backdropPath;
    private Double popularMovie;

    //  Movie object Constructor
    public Movie(String title, String posterPath, String description, String backdropPath, Double popularMovie) {

        this.title = title;
        this.posterPath = posterPath;
        this.description = description;
        this.backdropPath = backdropPath;
        this.popularMovie = popularMovie;

    }

    @Override
    public int describeContents() {

        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.description);
        dest.writeString(this.backdropPath);
        dest.writeDouble(this.popularMovie);

    }

    protected Movie(Parcel source) {

        this.title = source.readString();
        this.posterPath = source.readString();
        this.description = source.readString();
        this.backdropPath = source.readString();
        this.popularMovie = source.readDouble();

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