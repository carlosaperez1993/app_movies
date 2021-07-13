package com.app.movieapp.models

import com.google.gson.annotations.SerializedName


data class Seasons (

	@SerializedName("air_date") val air_date : String = "",
	@SerializedName("episode_count") val episode_count : Int=0,
	@SerializedName("id") val id : Int=0,
	@SerializedName("name") val name : String="",
	@SerializedName("overview") val overview : String="",
	@SerializedName("poster_path") val poster_path : String="",
	@SerializedName("season_number") val season_number : Int=0
)