package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import retrofit2.Retrofit
import rx.Single
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    val apiManager : ApiManager = ApiManager()
        var characterResult :Single<CharacterResult> = apiManager.RetrieveCharacters()

    }
}
