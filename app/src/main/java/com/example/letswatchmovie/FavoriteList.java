package com.example.letswatchmovie;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favouriteList")
public class FavoriteList {

    @PrimaryKey
    private String movieId;

    @ColumnInfo(name = "movie Poster")
    private String moviePoster;

    @ColumnInfo(name = "movie Title")
    private String movieTitle;

    @ColumnInfo(name = "movie Release Date")
    private String movieRelDate;

    public String getMovieId() {
        return movieId;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieRelDate() {
        return movieRelDate;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public void setMovieRelDate(String movieRelDate) {
        this.movieRelDate = movieRelDate;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}