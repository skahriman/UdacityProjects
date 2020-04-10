package com.exmaple.android.popularmovies.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final private static String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    final private static String BASE_URL_SORTED = "http://api.themoviedb.org/3/movie/top_rated";
    final private static String PARAM_API_KEY = "api_key";

    //TODO remove this api key when you publish your app
    final private static String PARAM_API_VALUE = "3ba8d51a5df2e04fe0ffedf1e9a8eec4";
    final private static String PARAM_SORT_KEY = "sort_by";
    final private static String PARAM_SORT_VALUE = "vote_average.desc";
    final private static String PARAM_PAGE_KEY = "page";
    final private static String PARAM_PAGE_VALUE = "1";

    public static URL buildUrl() throws MalformedURLException {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, PARAM_API_VALUE)
                .build();
        return new URL(builtUri.toString());
    }

    public static URL buildUrlSorted() throws MalformedURLException {
        Uri builtUri = Uri.parse(BASE_URL_SORTED).buildUpon()
//                .appendQueryParameter(PARAM_SORT_KEY, PARAM_SORT_VALUE)
                .appendQueryParameter(PARAM_API_KEY, PARAM_API_VALUE)
//                .appendQueryParameter(PARAM_PAGE_KEY, PARAM_PAGE_VALUE)
                .build();
        return new URL(builtUri.toString());
    }



    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
