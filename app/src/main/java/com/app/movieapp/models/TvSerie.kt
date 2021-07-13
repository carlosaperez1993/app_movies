package com.app.movieapp.models

import com.google.gson.annotations.SerializedName

data class TvSerie (

	@SerializedName("poster_path") val imgUrl : String,
	@SerializedName("popularity") val popularity : Double,
	@SerializedName("id") val id : Long,
	@SerializedName("backdrop_path") val backdrop_path : String,
	@SerializedName("vote_average") val vote_average : String,
	@SerializedName("overview") val overview : String,
	@SerializedName("first_air_date") val release_date : String,
	@SerializedName("origin_country") val origin_country : List<String>,
	@SerializedName("genres") val genres : List<Genre> = listOf(),
	@SerializedName("original_language") val original_language : String,
	@SerializedName("vote_count") val vote_count : Int,
	@SerializedName("name") val title : String,
	@SerializedName("original_name") val original_name : String,
	@SerializedName("seasons") val seasons : List<Seasons> = listOf()

)