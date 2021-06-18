package com.example.letswatchmovie;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteListActivity extends AppCompatActivity {
    private RecyclerView favRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favRecyclerView = findViewById(R.id.favRecyclerView);
        favRecyclerView.setHasFixedSize(true);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getFavData();
    }

    private void getFavData() {
        List<FavoriteList> favoriteLists=MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();

        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getApplicationContext(), favoriteLists);
        favRecyclerView.setAdapter(favoriteAdapter);
    }


}
