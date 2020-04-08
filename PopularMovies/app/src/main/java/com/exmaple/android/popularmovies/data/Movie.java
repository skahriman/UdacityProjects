package com.exmaple.android.popularmovies.data;

public class Movie {

    private String id;
    private String title;
    private String release_date;
    private String poster_path;
    private String vote_average;
    private String overview;

    public Movie(String id, String title, String release_date, String poster_path, String vote_average, String overview) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.overview = overview;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }
}
