package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.data.exceptions.ApiEmptyResultException
import com.ynov.kotlin.rickmorty.data.remote.EpisodeResult
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EpisodesViewModel : BaseViewModel() {

    // TODO il n'y a pas de raison de mettre une mutablelist dans un livadata
    //  on modifie toujours le contenu du livedata mais jamais l'objet contenu en lui-même
    var mItems: MutableLiveData<MutableList<Episode>> = MutableLiveData()
    private var onSubscribe: Disposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        loadData()
    }

    override fun onCleared() {
        onSubscribe?.dispose()

        super.onCleared()
    }


    fun loadData() {
        mIsLoading.value = true
        val characterResult: Single<EpisodeResult> = RMApplication.app.episodeRepository.getEpisodes()
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
