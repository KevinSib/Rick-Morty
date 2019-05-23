package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.exceptions.ApiEmptyResultException
import com.ynov.kotlin.rickmorty.presentation.extensions.showSnackbar
import com.ynov.kotlin.rickmorty.presentation.viewModels.BaseViewModel

abstract class BaseFragment<T: BaseViewModel> : Fragment() {

    protected abstract val viewModelClass: Class<T>

    abstract var layoutId: Int

    protected val viewModel: T by lazy {
        ViewModelProviders.of(this).get(viewModelClass)
    }

    //region Default Methods

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
        initViewModelObserver()
    }

    //endregion

    //region Methods

    open fun initViewModelObserver() {
        viewModel.mError.observe(this, Observer {
            var errMsg = getString(R.string.error_default)
            if (it is ApiEmptyResultException) {
                errMsg = getString(R.string.error_api_empty)
            }
            errMsg += " : ${it.message}"
            stopLoading()
            view?.showSnackbar(errMsg)
            onError(it)
        })
        viewModel.mIsLoading.observe(this, Observer {
            if (it)
                startLoading()
            else
                stopLoading()
        })
    }

    //endregion

    //region Abstract Methods

    abstract fun startLoading()

    abstract fun stopLoading()

    abstract fun onError(err: Throwable)

    //endregion

}