package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.MutableLiveData
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel : BaseViewModel() {

    // TODO le livedata peut être val
    var mItem: MutableLiveData<Character> = MutableLiveData()
    private var onSubscribe: Disposable? = null

    fun start(id: Long) {
        // TODO utiliser .postValue() plutôt que .value =
        mIsLoading.value = true
        val characterResult: Single<Character> = RMApplication.app.characterRepository.getCharacterById(id)
        onSubscribe = characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    mIsLoading.postValue(false)
                    mItem.postValue(it)
                },
                onError = {
                    mError.postValue(it)
                }
            )
    }

    override fun onCleared() {
        onSubscribe?.dispose()
        super.onCleared()
    }

}