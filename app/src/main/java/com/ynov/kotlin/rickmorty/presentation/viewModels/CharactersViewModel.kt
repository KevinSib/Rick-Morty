package com.ynov.kotlin.rickmorty.presentation.viewModels

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
    private var onSubscribe: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        loadData()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        onSubscribe?.dispose()
    }

    fun loadData() {
        mIsLoading.value = true
        val characterResult: Single<CharacterResult> = RMApplication.app.characterRepository.getCharacters()
        onSubscribe = characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { res ->
                mIsLoading.value = false
                mItems.value = res.results.toMutableList()
            }
    }
}

