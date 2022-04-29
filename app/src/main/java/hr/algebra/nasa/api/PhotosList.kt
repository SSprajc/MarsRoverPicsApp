package hr.algebra.nasa.api

import com.google.gson.annotations.SerializedName

data class PhotosList(
    @SerializedName("photos") val photos : List<Photos>
)
