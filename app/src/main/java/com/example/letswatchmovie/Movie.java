package com.example.letswatchmovie;

public class Movie {
    private String mTitle;
    private String mVote;
    private String mPosterId = "";

    public Movie(String title , String vote , String posterId)
    {
        this.mPosterId = posterId;
        this.mTitle = title;
        this.mVote = vote;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmVote() {
        return mVote;
    }

    public String getmPosterId() {
        return mPosterId;
    }
}
