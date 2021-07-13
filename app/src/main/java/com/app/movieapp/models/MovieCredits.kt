package com.app.movieapp.models

import com.google.gson.annotations.SerializedName

//for known movies of a cast

data class MovieCredits(@SerializedName("cast") val known_movies : List<Movie> = listOf())