package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharactersViewModel : ViewModel() {

    var mItems: MutableLiveData<MutableList<Character>> = MutableLiveData()

    fun loadData() {
        var characterResult: Single<CharacterResult> = RMApplication.app.dataRepository.retrieveCharacter()
        var a =  characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                mItems.value = res.results.toMutableList()
            })
    }

}