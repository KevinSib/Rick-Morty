package com.ynov.kotlin.rickmorty.presentation.activities

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.fragments.CharactersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main_container, CharactersFragment.newInstance())
            .commit()
    }

}
