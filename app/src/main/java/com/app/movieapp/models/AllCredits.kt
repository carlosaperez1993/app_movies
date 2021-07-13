package com.app.movieapp.models

import com.google.gson.annotations.SerializedName

data class AllCredits(
    @SerializedName("cast") val cast : List<Cast> = listOf(),
    @SerializedName("crew") val crew : List<Crew> = listOf()
)