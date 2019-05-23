package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.exceptions.ApiEmptyResultException
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharactersViewModel : BaseViewModel() {

    var mItems: MutableLiveData<MutableList<Character>> = MutableLiveData()
    private var onSubscribe: Disposable? = null


    // TODO mieux vaut appeler loadData() dans le init {} de la classe CharactersViewModel
    //  puisque c'est la première chose qui s'exécute après le constructeur
    //  et la liste reste dans le livedata tant que le viewmodel existe
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        loadData()
    }

    override fun onCleared() {
        onSubscribe?.dispose()
        super.onCleared()
    }

    fun loadData() {
        // TODO utiliser .postValue() plutôt que .value =
        mIsLoading.value = true
        val characterResult: Single<CharacterResult> = RMApplication.app.characterRepository.getCharacters()
        onSubscribe = characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    mIsLoading.postValue(false)
                    mItems.postValue(it.results.toMutableList())
                },
                onError = {
                    mError.postValue(it)
                }
            )
    }

}

