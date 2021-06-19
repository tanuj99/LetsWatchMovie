package com.example.letswatchmovie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteList.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

}
