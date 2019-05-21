package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), LifecycleObserver {

    var mIsLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        mIsLoading.value = false
    }

}