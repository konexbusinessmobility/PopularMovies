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

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {

            return new Movie(source);

        }

        @Override
        public Movie[] newArray(int size) {

            return new Movie[size];

        }
    };

    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public String getPosterPath() {

        return "http://image.tmdb.org/t/p/w180" + posterPath;

    }

    public void setPosterPath(String poster) {

        this.posterPath = poster;
    }

    public String getOverview() {

        return overview;

    }

    public void setOverview(String overview) {

        this.overview = overview;

    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }


    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getBackdropPath() {

        return "http://image.tmdb.org/t/p/w500" + backdropPath;

    }

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

}