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
        mDelayHandler?.let {
            it.postDelayed(mRunnable, SPLASH_DELAY)
        }

    }

    public override fun onDestroy() {
        mDelayHandler?.let {
            it.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}
