package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.DataRepository
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.fragments.CharactersFragment
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var fragmentContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.activity_main_container)
        initFragment()
    }
    
    private fun initFragment() {
        CharactersFragment.newInstance()?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.activity_main_container, it)
            transaction.commit()
        }
    }

}
