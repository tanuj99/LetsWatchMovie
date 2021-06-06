package com.example.letswatchmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    List<Movie> movieList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        try{
            getMovieData();
        }catch (Exception e){
            Log.e("On Create Error","Unable to get Movie data");
        }
    }

    private void getMovieData() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=8c20ae237f85f1e8e62140e5509ca3db&language=en-US";
        RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="https://www.google.com";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray movieJsonArray = object.getJSONArray("results");

                            for(int i=0; i<movieJsonArray.length(); i++){
                                JSONObject movieObject = movieJsonArray.getJSONObject(i);

                                Movie movie = new Movie(movieObject.getString("title"),
                                        movieObject.getString("vote_average"),
                                        movieObject.getString("poster_path"),movieObject.getString("release_date"));

                                movieList.add(movie);
                            }

                            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), movieList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("JSON Error", "JSON Error");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error" , "Volley error");

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        // Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }


}

