package com.app.movieapp.data.repository.local

import com.app.movieapp.data.db.AppDatabase
import com.app.movieapp.models.FavMovie
import io.reactivex.Observable

class LocalDataSource constructor(private val db : AppDatabase) {

    fun getAllFavMovies() : Observable<List<FavMovie>>{
        return  db.favDao().getAllFavMovies()
    }

    fun clearAllFavMovies(){
        db.favDao().clearAllFavMovies()
    }

    fun addFavMovie(movie: FavMovie){
        return db.favDao().addFavMovie(movie)
    }

    fun removeFavMovieById(id : Long){
        db.favDao().removeFavMovieById(id)
    }
    fun existAsFavorite(id : Long) : Observable<Boolean>{
        return  db.favDao().existAsFavorite(id)
    }

}