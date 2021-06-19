package com.example.letswatchmovie;

import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    String baseURL = "https://api.themoviedb.org/3/movie/popular?api_key=8c20ae237f85f1e8e62140e5509ca3db&language=en-US";

    List<Movie> movieList;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    public static FavoriteDatabase favoriteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        favoriteDatabase = Room.databaseBuilder(getApplicationContext(),FavoriteDatabase.class,"myFavDB")
                .allowMainThreadQueries().build();

        try{
            getMovieData();
        }catch (Exception e){
            Log.d("On Create Error","Unable to get Movie data");
        }
    }



    protected void getMovieData() {
        String url = baseURL;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
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

                        adapter = new MovieAdapter(getApplicationContext(), movieList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("JSON Error", "JSON Error");
                    }

                }, error -> Log.e("Volley Error" , "Volley error"));
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main , menu);

        MenuItem searchItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adapter.getFilter().filter(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });

        MenuItem favButton = menu.findItem(R.id.favBtn);
        AppCompatButton button = (AppCompatButton) favButton.getActionView();
        //button.setMaxWidth(Integer.MAX_VALUE);

        button.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,FavoriteListActivity.class)));

        return super.onCreateOptionsMenu(menu);
    }

}

