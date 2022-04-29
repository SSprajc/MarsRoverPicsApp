package hr.algebra.nasa.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_URL = "https://api.nasa.gov/mars-photos/"

interface NasaApi {
    @GET("api/v1/rovers/{rover}/photos")
    fun fetchItems(
        @Path("rover") rover: String,
        @Query("page") page: Int,
        @Query("sol") sol: String,
        @Query("api_key") key: String
    ): Call<PhotosList>
}