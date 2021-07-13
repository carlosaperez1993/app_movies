package com.app.movieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fav_movies")
 class FavMovie(
    @PrimaryKey val movieId : Long = 0,
    val adult : Boolean = false,
    val imgUrl : String = "",
    val runtime : Long = 0,
    val title : String = "",
    val overview : String = "",
    val release_date : String = "",
    val vote_average : String = ""
)