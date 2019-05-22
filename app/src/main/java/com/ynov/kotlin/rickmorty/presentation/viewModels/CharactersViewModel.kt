package com.ynov.kotlin.rickmorty.presentation.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CharactersViewModel : BaseViewModel() {

    var mItems: MutableLiveData<MutableList<Character>> = MutableLiveData()
    private var onSubscribe: Disposable? = null;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        val characterResult: Single<CharacterResult> = RMApplication.app.dataRepository.retrieveCharacter()
        onSubscribe = characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { res ->
                mItems.value = res.results.toMutableList()
            }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        onSubscribe?.dispose()
    }
}

