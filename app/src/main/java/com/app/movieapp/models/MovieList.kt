package com.app.movieapp.models

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page") val page:Int = 0,
    @SerializedName("results") val results:List<Movie> = listOf(),
    @SerializedName("total_results") val totalResults:Int = 0,
    @SerializedName("total_pages") val totalPages:Int = 0
)