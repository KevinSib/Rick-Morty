package com.ynov.kotlin.rickmorty.presentation.viewModels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), LifecycleObserver {

    // TODO pour les noms de varibable il est préférable de leur donner le type en plus
    //  isLoadingLiveData et errorLiveData
    //  en plus pour une class abstract cela permet de bien comprendre ce qu'est errorLiveData
    //  dans les classes filles sans avoir à ouvrir ce fichier.
    //  Aussi, les LiveData peuvent être val puisqu'on ne les modifie jamais
    var mIsLoading: MutableLiveData<Boolean> = MutableLiveData()
    var mError: MutableLiveData<Throwable> = MutableLiveData()

    init {
        mIsLoading.value = false
    }

}