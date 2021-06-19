package com.example.letswatchmovie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void addData(FavoriteList favoriteList);

    @Query("select * from favouriteList")
    List<FavoriteList> getFavoriteData();

    @Query("SELECT EXISTS (SELECT 1 FROM favouriteList WHERE movieId=:id)")
    int isFavorite(String id);

    @Delete
    void delete(FavoriteList favoriteList);


}
