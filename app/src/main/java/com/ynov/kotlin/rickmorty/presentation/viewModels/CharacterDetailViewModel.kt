package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.*
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel : BaseViewModel() {

    var mItem: MutableLiveData<Character> = MutableLiveData()

    fun start(id: Long) {
        mIsLoading.value = true
        var characterResult: Single<Character> = RMApplication.app.dataRepository.retrieveDetailCharacter("$id")
        var a =  characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                mIsLoading.value = false
                mItem.value = res
            })
    }

}