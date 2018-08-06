
package com.konexbusinessmobility.popularmovies.utilities;

import android.content.Context;
import android.util.Log;

import com.konexbusinessmobility.popularmovies.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

    public final class MoviesJsonUtils {
        private static final String TAG = "MoviesJsonUtils";

        /**
         * This method parses JSON from a response and returns an array of strings
         * describing popular movies.
         *
         * @param moviesJsonStr JSON response from server.
         * @return Array of strings describing movies
         * @throws JSONException If JSON data cannot be properly parsed
         */
        public static List<Movie> getMoviesStringsFromJson(Context context, String moviesJsonStr) throws JSONException {
            /* Movie list */
            final String MOVIE_LIST = "results";
            final String MOVIE_ID = "id";
            final String TITLE = "title";
            final String POSTER = "poster_path";
            final String DESCRIPTION = "description";
            final String BACKDROP = "backdrop_path";

            // Create an empty ArrayList to add movies to.
            List<Movie> movies = new ArrayList<>();

            // Create a JSONObject from the JSON response string
            JSONObject moviesJson = new JSONObject(moviesJsonStr);

            // Extract the JSONArray associated with the key called "results",
            JSONArray movieArray = moviesJson.getJSONArray(MOVIE_LIST);


            for (int i = 0; i < movieArray.length(); i++) {

                /* Get the JSON object representing a movie */
                JSONObject movieJson = movieArray.getJSONObject(i);

                /* Get the movie id */
                String id = movieJson.getString(MOVIE_ID);

                /* Get the movie title */
                String title = movieJson.getString(TITLE);

                /* Get the movie poster */
                String posterPath = movieJson.getString(POSTER);
                Log.v(TAG, "poster" + posterPath);

                /* Get the movie backdrop */
                String backdropPath = movieJson.getString(BACKDROP);
                Log.v(TAG, "backdrop" + backdropPath);

                /* Get the movie backdrop */
                String description = movieJson.getString(DESCRIPTION);
                Log.v(TAG, "description" + description);


                /**
                 * Create a new Movie object with selected properties.
                 */
                Movie movie = new Movie(title, id, posterPath, description, backdropPath);

                movies.add(movie);
            }

            return movies;

        }
    }
