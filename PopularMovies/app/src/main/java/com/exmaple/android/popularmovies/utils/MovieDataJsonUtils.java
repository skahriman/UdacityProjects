package com.exmaple.android.popularmovies.utils;

import android.content.Context;

import com.exmaple.android.popularmovies.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDataJsonUtils {
    public static Movie[] getMoviesFromJson(Context context, String movieJsonString) throws JSONException {

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

            Movie item = new Movie(id, title, release_date, backdrop_path, poster_path, vote_average, overview);
            resultArray[i] = item;
        }

        return resultArray;
    }
}
