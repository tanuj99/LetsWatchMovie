package com.example.letswatchmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    //public static String baseUrl = "https://api.themoviedb.org/3/movie/popular?api_key=8c20ae237f85f1e8e62140e5509ca3db&language=en-US";

    List<Movie> movieList;
    RecyclerView recyclerView;
    //private Calendar MySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            getMovieData();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getMovieData() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=8c20ae237f85f1e8e62140e5509ca3db&language=en-US";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONObject object = new JSONObject((Map) response);
                        JSONArray movieJsonArray = object.getJSONArray("results");

                        for(int i=0; i<movieJsonArray.length(); i++){
                            JSONObject movieObject = movieJsonArray.getJSONObject(i);

                            Movie movie = new Movie(movieObject.getString("title"),
                                    movieObject.getString("vote_average"),
                                    movieObject.getString("poster_path"));

                            movieList.add(movie);
                        }

                        MovieAdapter adapter = new MovieAdapter(getApplicationContext(), movieList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show());

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }


}