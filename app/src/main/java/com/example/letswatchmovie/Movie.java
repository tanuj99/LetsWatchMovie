package com.example.letswatchmovie;

public class Movie {
    private final String mTitle;
    private final String mVote;
    private final String mPosterId;
    private final String mRelDate;

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
