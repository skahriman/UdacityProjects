package com.example.android.popularmovies.data;

public class Movie {
    final private String id;
    final private String title;
    final private String release_date;
    final private String backdrop_path;
    final private String poster_path;
    final private String vote_average;
    final private String overview;
    private String[] urlForTrailers;

    public Movie(String id, String title, String release_date, String backdrop_path, String poster_path, String vote_average, String overview, String[] urlForTrailers) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.overview = overview;
        this.urlForTrailers = urlForTrailers;
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

    public String getBackdrop_path() {
        return backdrop_path;
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

    public String[] getUrlForTrailers() {
        return urlForTrailers;
    }

    public void setUrlForTrailers(String[] urlForTrailers) {
        this.urlForTrailers = urlForTrailers;
    }
}
