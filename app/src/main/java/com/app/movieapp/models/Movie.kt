package com.app.movieapp.models

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("id") val movieId : Long = 0,
    @SerializedName("adult") val adult : Boolean = false,
    @SerializedName("poster_path") val imgUrl : String = "",
    @SerializedName("genres") val genres : List<Genre> = listOf(),
    @SerializedName("runtime") val runtime : Long = 0,
    @SerializedName("title") val title : String = "",
    @SerializedName("overview") val overview : String = "",
    @SerializedName("release_date") val release_date : String = "",
    @SerializedName("credits") val credits : AllCredits? = null,
    @SerializedName("vote_average") val vote_average : String = ""
    )