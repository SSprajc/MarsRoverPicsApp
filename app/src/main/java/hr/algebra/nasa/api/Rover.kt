package hr.algebra.nasa.api

import com.google.gson.annotations.SerializedName

data class Rover(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("landing_date") val landing_date: String,
    @SerializedName("launch_date") val launch_date: String,
    @SerializedName("status") val status: String
)
