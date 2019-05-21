package com.ynov.kotlin.rickmorty.presentation.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.extensions.showLoading
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharacterDetailViewModel
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharactersViewModel


class CharacterDetailFragment : Fragment() {

    var loader: ProgressDialog? = null

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
        arguments?.let {
            viewModel?.start(it.getLong(CHARACTER_ID))
        }
    }

    companion object {
        val CHARACTER_ID: String = "CHAR_ID"
        @JvmStatic
        fun newInstance(id: Long) = CharactersFragment().apply {
            arguments = Bundle().apply {
                putLong(CHARACTER_ID, id)
            }
        }
    }

    //endregion

    //region Methods

    private fun initViewModelObserver() {
        viewModel.mIsLoading.observe(this, Observer {
            if (it) {
                context?.let {
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
