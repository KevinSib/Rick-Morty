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
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var fragmentContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.activity_main_container)
        initFragment()
    }

        val apiManager: ApiManager = ApiManager()
        var characterResult: Single<CharacterResult> = DataRepository(apiManager).retrieveCaracter()

        var a = characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { it -> print(it.results.get(0))
                }, onError = {it-> print(it.)

                }
            )
    }

}
