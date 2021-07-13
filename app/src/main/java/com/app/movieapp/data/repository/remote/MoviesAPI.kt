package com.app.movieapp.data.repository.remote

import com.app.movieapp.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface MoviesAPI {
    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") movie_id: Long, @Query("api_key") apiKey: String, @Query("append_to_response") word: String): Observable<Movie>

    @GET("person/{id}")
    fun getCastDetail(@Path("id") cast_id: Long, @Query("api_key") apiKey: String, @Query("append_to_response") movie_credits: String): Observable<Cast>

    @GET("movie/top_rated")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int?): Observable<MovieList>

    @GET("tv/top_rated")
    fun getPopularTvSeries(@Query("api_key") apiKey: String,  @Query("page") page: Int?): Observable<TvSeriesList>

    @GET("tv/{id}")
    fun getTvSerieDetail(@Path("id") id: Long, @Query("api_key") apiKey: String, @Query("append_to_response") word: String): Observable<TvSerieDetail>
}