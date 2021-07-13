package com.app.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.movieapp.models.FavMovie

@Database(entities = [FavMovie::class],exportSchema = false,version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favDao() : MoviesDao
}