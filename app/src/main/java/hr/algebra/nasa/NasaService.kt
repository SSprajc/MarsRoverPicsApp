package hr.algebra.nasa

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.nasa.api.NasaFetcher

private const val JOB_ID = 1

class NasaService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        println(intent.getStringExtra("sol"))
        println(intent.getStringExtra("rover"))

        NasaFetcher(this).fetchItems(
            intent.getStringExtra("rover")!!,
            intent.getStringExtra("sol")!!
        )

    }

    companion object {
        fun enqueue(context: Context, intent: Intent, sol: String, rover: String) {
            val intent1 = intent.apply {
                putExtra("sol", sol)
                putExtra("rover", rover)
            }
            enqueueWork(context, NasaService::class.java, JOB_ID, intent1)
        }
    }
}