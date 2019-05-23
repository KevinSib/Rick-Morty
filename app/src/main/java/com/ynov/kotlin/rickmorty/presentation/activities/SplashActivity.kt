package com.ynov.kotlin.rickmorty.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ynov.kotlin.rickmorty.R

class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            startActivity(MainActivity.newIntent(this))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()
        // TODO ici le let est inutile, il suffit de faire :
        //  mDelayHandler?.postDelayed(mRunnable, SPLASH_DELAY)
        mDelayHandler?.let {
            it.postDelayed(mRunnable, SPLASH_DELAY)
        }

    }

    public override fun onDestroy() {
        // TODO pareil pour le let
        mDelayHandler?.let {
            it.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}
