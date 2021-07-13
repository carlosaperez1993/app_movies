package com.app.movieapp.models

import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("name") val name : String = "",
    @SerializedName("job") val job : String = "",
    @SerializedName("department") val department : String = "",
    @SerializedName("profile_path") val profile_path : String = ""
)