package com.ynov.kotlin.rickmorty.presentation.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel : BaseViewModel() {

    var mItem: MutableLiveData<Character> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun start(id: Long) {
        val characterResult: Single<Character> = RMApplication.app.dataRepository.retrieveDetailCharacter("$id")
        characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { res ->
                mItem.value = res
            }
    }
}