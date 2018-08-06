package com.konexbusinessmobility.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    public String mTitle;
    private String mId;
    public String mPosterPath;
    public String mDescription;
    private String mBackdropPath;

    //  Movie object Constructor
    public Movie(String title, String id, String posterPath, String description, String backdropPath) {

        this.mTitle = title;
        this.mId = id;
        this.mPosterPath = posterPath;
        this.mDescription = description;
        this.mBackdropPath = backdropPath;

    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getDescription() {
        return mDescription;
    }

}