package com.app.movieapp.data.repository

import android.annotation.SuppressLint
import com.app.movieapp.data.repository.local.LocalDataSource
import com.app.movieapp.data.repository.remote.RemoteDataSource
import com.app.movieapp.models.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesRepository constructor(
    private val localDataSource : LocalDataSource,
    private val remoteDataSource : RemoteDataSource){

    fun getPopularMovies(page: Int?=1) : Observable<MovieList>{
        return remoteDataSource.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }


    fun getMovieDetail(id: Long) : Observable<Movie>{
        return remoteDataSource.getMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun getPopularTvSeries(page: Int?=1) : Observable<TvSeriesList>{
        return remoteDataSource.getPopularTvSeries(page)
    }

    fun getTvSerieDetail(id: Long) : Observable<TvSerieDetail>{
        return remoteDataSource.getTvSerieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun getCastDetail(id : Long) : Observable<Cast>{
        return remoteDataSource.getCastDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
    @SuppressLint("CheckResult")
    fun saveFavorite(favMovie: FavMovie) {
        localDataSource.addFavMovie(favMovie)
    }
    fun getFavorites(): Observable<List<FavMovie>> {
        return localDataSource.getAllFavMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
    fun existAsFavorite(id:Long): Observable<Boolean> {
        return localDataSource.existAsFavorite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
    fun deleteFavorite(id:Long) {
        return localDataSource.removeFavMovieById(id)
    }


}