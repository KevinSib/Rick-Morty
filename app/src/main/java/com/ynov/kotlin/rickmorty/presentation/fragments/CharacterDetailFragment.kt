package com.ynov.kotlin.rickmorty.presentation.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.extensions.showLoading
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharacterDetailViewModel


class CharacterDetailFragment(private var characterId: Long) : Fragment() {

    private var loader: ProgressDialog? = null

    private val viewModel: CharacterDetailViewModel by lazy {
        ViewModelProviders.of(this).get(CharacterDetailViewModel::class.java)
    }

    //region Default Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        initViewModelObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(id = characterId)
    }

    companion object {
        fun newInstance(id: Long) = CharacterDetailFragment(id)
    }

    //endregion

    //region Methods

    private fun initViewModelObserver() {
        viewModel.mIsLoading.observe(this, Observer { it ->
            if (it) {
                context?.let { it ->
                    loader = it.showLoading()
                }
            } else {
                loader?.dismiss()
            }
        })
        viewModel.mItem.observe(this, Observer {
            //  TODO customize view
        })
    }

    //endregion

}
