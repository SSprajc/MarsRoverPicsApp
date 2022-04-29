package hr.algebra.nasa.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.nasa.DATA_IMPORTED
import hr.algebra.nasa.NASA_PROVIDER_URI
import hr.algebra.nasa.NasaReceiver
import hr.algebra.nasa.framework.sendBroadcast
import hr.algebra.nasa.framework.setBooleanPreference
import hr.algebra.nasa.handler.downloadImageAndStore
import hr.algebra.nasa.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class NasaFetcher(private val context: Context) {

    private var nasaApi: NasaApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nasaApi = retrofit.create(NasaApi::class.java)
    }

    fun fetchItems(rover: String, sol: String) {
        context.contentResolver.delete(NASA_PROVIDER_URI, null, null)
        context.setBooleanPreference(DATA_IMPORTED, false)
        val request = nasaApi.fetchItems(rover, 1, sol, "DEMO_KEY")

        request.enqueue(object : Callback<PhotosList> {
            override fun onResponse(call: Call<PhotosList>, response: Response<PhotosList>) {
                response.body()?.photos?.let {
                    populateItems(it)
                }
            }

            override fun onFailure(call: Call<PhotosList>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun populateItems(photos: List<Photos>) {
        GlobalScope.launch {

            photos.forEach {
                /*val picturePath = downloadImageAndStore(
                    context,
                    it.img_src,
                    it.rover.name,
                    it.id.toString()
                )
                 */

                val values = ContentValues().apply {
                    put(Item::picturePath.name, it.img_src ?: "")
                    //put(Item::picturePath.name, picturePath ?: "")
                    put(Item::roverName.name, it.rover.name)
                    put(Item::cameraName.name, it.camera.full_name)
                    put(Item::earthDate.name, it.earth_date)
                    put(Item::roverStatus.name, it.rover.status)
                    put(Item::landingDate.name, it.rover.landing_date)
                }
                context.contentResolver.insert(NASA_PROVIDER_URI, values)
            }
            context.setBooleanPreference(DATA_IMPORTED, true)
            context.sendBroadcast<NasaReceiver>()
        }
    }
}