package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

abstract class BaseFragment<T: ViewModel> : Fragment() {

    protected abstract val viewModelClass: Class<T>

    abstract var layoutId: Int

    abstract fun initViewModelObserver()

    protected val viewModel: T by lazy {
        ViewModelProviders.of(this).get(viewModelClass)
    }

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

}