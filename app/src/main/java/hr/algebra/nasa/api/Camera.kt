package hr.algebra.nasa.api

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rover_id") val rover_id: Int,
    @SerializedName("full_name") val full_name: String
)
