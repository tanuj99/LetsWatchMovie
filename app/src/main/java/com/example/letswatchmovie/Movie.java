package com.example.letswatchmovie;

public class Movie {
    private String mTitle;
    private String mVote;
    private String mPosterId;
    private String mRelDate;

    public Movie(String title , String vote , String posterId , String relDate)
    {
        this.mPosterId = posterId;
        this.mTitle = title;
        this.mVote = vote;
        this.mRelDate = relDate;
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

    public String getmRelDate() {
        return mRelDate;
    }
}
