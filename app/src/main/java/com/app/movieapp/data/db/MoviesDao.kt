package com.app.movieapp.data.db

import androidx.room.*
import androidx.room.Dao
import com.app.movieapp.models.FavMovie
import io.reactivex.Observable

@Dao
interface MoviesDao {

    @Query("select * from fav_movies")
    fun getAllFavMovies() : Observable<List<FavMovie>>

    @Query("delete from fav_movies")
    fun clearAllFavMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavMovie(favMovie: FavMovie)

    @Query("delete from fav_movies where movieId = :movieid")
    fun removeFavMovieById(movieid : Long)

    @Query("SELECT COUNT(*) FROM fav_movies WHERE movieId=:id LIMIT 1")
    fun existAsFavorite(id: Long): Observable<Boolean>


}