package hr.algebra.nasa.api

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("id") val id: Int,
    @SerializedName("sol") val sol: Int,
    @SerializedName("camera") val camera: Camera,
    @SerializedName("img_src") val img_src: String,
    @SerializedName("earth_date") val earth_date: String,
    @SerializedName("rover") val rover: Rover
)
