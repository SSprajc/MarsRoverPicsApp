package hr.algebra.nasa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import hr.algebra.nasa.databinding.ActivitySplashScreenBinding
import hr.algebra.nasa.framework.*

private const val DELAY = 3000L
private const val EXR_REQ = 1
const val DATA_IMPORTED = "hr.algebra.nasa.data_imported"

class SplashScreenActivity() : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (intent.hasExtra("sol") && intent.hasExtra("rover")) {
            startAnimations()
            redirectWithParam(intent.getStringExtra("sol"), intent.getStringExtra("rover"))
        } else {
            startAnimations()
            redirect()
        }


    }

    private fun redirectWithParam(sol: String?, rover: String?) {
        println(sol)
        println(rover)

        Intent(this, NasaService::class.java).apply {
            NasaService.enqueue(
                this@SplashScreenActivity,
                this,
                sol!!,
                rover!!
            )
            //callDelayed(DELAY) { startActivity<HostActivity>() }
            //putExtra("sol", sol.toString())
            //putExtra("rover", rover.toString())
        }
    }


    private fun startAnimations() {
        binding.ivSplash.startAnimation(R.anim.rotate)
        binding.tvSplash.startAnimation(R.anim.blink)
    }

    private fun redirect() {
        if (isOnline() || getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(DELAY) { startActivity<HostActivity>() }
        } else {
            binding.tvSplash.text = getString(R.string.no_internet)
            callDelayed(DELAY) { finish() }
        }
    }
}
