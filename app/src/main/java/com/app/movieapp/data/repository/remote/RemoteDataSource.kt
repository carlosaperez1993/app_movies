package com.app.movieapp.data.repository.remote

import com.app.movieapp.models.*
import io.reactivex.Observable

class RemoteDataSource constructor(private val movieAPI : MoviesAPI ) {

    fun getPopularMovies(page: Int?=1) : Observable<MovieList>{
        return movieAPI.getPopularMovies("027d4a3e0b239cbb72f27bc405136691",page)
    }

    fun getCastDetail(id : Long,movie_credits : String ="movie_credits") : Observable<Cast>{
        return movieAPI.getCastDetail(id,"027d4a3e0b239cbb72f27bc405136691",movie_credits)
    }

    fun getMovieDetail(id : Long,credits : String = "credits") : Observable<Movie>{
        return movieAPI.getMovieDetail(id,"027d4a3e0b239cbb72f27bc405136691",credits)
    }

    fun getPopularTvSeries(page: Int?=1) : Observable<TvSeriesList>{
        return movieAPI.getPopularTvSeries("027d4a3e0b239cbb72f27bc405136691", page)
    }
    fun getTvSerieDetail(id : Long,credits : String = "credits") : Observable<TvSerieDetail>{
        return movieAPI.getTvSerieDetail(id,"027d4a3e0b239cbb72f27bc405136691",credits)
    }




}