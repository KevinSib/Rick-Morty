package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.data.exceptions.ApiEmptyResultException
import com.ynov.kotlin.rickmorty.presentation.activities.CharacterDetailActivity
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter.IRecyclerViewManager
import com.ynov.kotlin.rickmorty.presentation.adapters.CharactersRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.extensions.showMessage
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.ClickHandler
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*


class CharactersFragment : BaseFragment<CharactersViewModel>(),
    IRecyclerViewManager<Character>,
    BaseViewHolder.IItemOnClickListener<Character> {

    override val viewModelClass = CharactersViewModel::class.java

    override var layoutId: Int = R.layout.fragment_characters

    override val onClickListenerManager: BaseViewHolder.IItemOnClickListener<Character>
        get() = this

    override val items: MutableList<Character>
        get() {
            viewModel.let {
                it.mItems.value?.let {
                    return it
                }
            }
            return mutableListOf()
        }

    //region Default Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onStart() {
        super.onStart()
        fragment_characters_recyclerview?.let {

            val adapter = CharactersRecyclerViewAdapters()
            adapter.manager = this

            it.layoutManager = GridLayoutManager(context, 2)
            it.adapter = adapter

        }
        fragment_characters_swipe.setOnRefreshListener {
            viewModel.loadData()
        }
        fragment_characters_errorview.setRetryListener {
            viewModel.loadData()
        }
    }

    override fun initViewModelObserver() {
        super.initViewModelObserver()
        viewModel.mItems.observe(this, Observer {
            fragment_characters_swipe.isRefreshing = false
            fragment_characters_recyclerview?.adapter?.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance() = CharactersFragment()
    }

    //endregion

    //region RecyclerView Manager Methods

    override fun numberOfItem(): Int = items.size

    override fun getItemAtPosition(position: Int): Character = items[position]

    //endregion

    //region ItemClick Delegate Methods

    override var onClickBlock: ClickHandler<Character> = { obj, _ ->
        val newIntent = CharacterDetailActivity.newIntent(requireContext(), obj.id)
        startActivity(newIntent)
    }

    //endregion

    //region Loading management

    override fun startLoading() {
        fragment_characters_errorview.visibility = View.GONE
        fragment_character_progressbar.visibility = View.VISIBLE
        fragment_character_loading_group.visibility = View.GONE
    }

    override fun stopLoading() {
        fragment_characters_swipe.isRefreshing = false
        fragment_character_progressbar.visibility = View.GONE
        fragment_character_loading_group.visibility = View.VISIBLE
    }

    //endregion

    //region Error Management

    override fun onError(err: Throwable) {
        err.message?.let {
            fragment_characters_errorview.title = it
        } ?: run {
            fragment_characters_errorview.title = getString(R.string.error_default)
        }
        fragment_characters_errorview.visibility = View.VISIBLE
    }

    //endregion
}
