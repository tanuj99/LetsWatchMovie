package com.example.letswatchmovie;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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
