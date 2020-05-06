package com.example.android.popularmovies.utils;

import com.example.android.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MovieDataJsonUtils {
    public static final String YOUTUBE_URL = "www.youtube.com/watch?v=";
    public static Movie[] getMoviesFromJson(String movieJsonString) throws JSONException, IOException {

        JSONObject movieJsonData = new JSONObject(movieJsonString);

        JSONArray jsonArray = movieJsonData.getJSONArray("results");

        Movie[] resultArray = new Movie[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            String id = jsonArray.getJSONObject(i).getString("id");
            String title = jsonArray.getJSONObject(i).getString("original_title");
            String release_date = jsonArray.getJSONObject(i).getString("release_date");
            String backdrop_path = jsonArray.getJSONObject(i).getString("backdrop_path");
            String poster_path = jsonArray.getJSONObject(i).getString("poster_path");
            String vote_average = jsonArray.getJSONObject(i).getString("vote_average");
            String overview = jsonArray.getJSONObject(i).getString("overview");
            String[] trailerKeys = setMovieTrailerUrls(id);

            Movie item = new Movie(id, title, release_date, backdrop_path, poster_path, vote_average, overview, trailerKeys);
            resultArray[i] = item;
        }

        return resultArray;
    }

    private static String[] setMovieTrailerUrls(String id) throws IOException, JSONException {
        URL url = NetworkUtils.favoriteMovies(id);

        String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(url);
        if (responseFromHttpUrl == null) {
            return null;
        }

        JSONObject movieJsonData = new JSONObject(responseFromHttpUrl);
        JSONArray jsonArray = movieJsonData.getJSONArray("results");
        String[] resultArray = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            String key = jsonArray.getJSONObject(i).getString("key");
            resultArray[i] = YOUTUBE_URL+key;
        }
        return resultArray;
    }
}
