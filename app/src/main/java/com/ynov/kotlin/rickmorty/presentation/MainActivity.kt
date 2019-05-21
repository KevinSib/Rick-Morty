package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.DataRepository
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiManager: ApiManager = ApiManager()
        var characterResult: Single<CharacterResult> = DataRepository(apiManager).RetrieveCaracter()
        
       var a =  characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->


                print(res.results.get(0).id)
            }
        )
    }

}
