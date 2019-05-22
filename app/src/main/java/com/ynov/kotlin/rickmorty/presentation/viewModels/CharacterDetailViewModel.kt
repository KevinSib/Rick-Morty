package com.ynov.kotlin.rickmorty.presentation.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel : BaseViewModel() {

    var mItem: MutableLiveData<Character> = MutableLiveData()
    private var onSubscribe: Disposable? = null

    fun start(id: Long) {
        mIsLoading.value = true
        val characterResult: Single<Character> = RMApplication.app.characterRepository.getCharacterById(id)
        onSubscribe = characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { res ->
                mIsLoading.value = false
                mItem.value = res
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        onSubscribe?.dispose()
    }
}