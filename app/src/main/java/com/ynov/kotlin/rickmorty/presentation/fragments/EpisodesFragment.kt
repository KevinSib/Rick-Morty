package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.presentation.activities.CharacterDetailActivity
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter
import com.ynov.kotlin.rickmorty.presentation.adapters.CharactersRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.adapters.EpisodesRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.ClickHandler
import com.ynov.kotlin.rickmorty.presentation.viewModels.EpisodesViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesFragment : BaseFragment<EpisodesViewModel>(),
    BaseRecyclerViewAdapter.IRecyclerViewManager<Episode>,
    BaseViewHolder.IItemOnClickListener<Episode> {

    override val viewModelClass = EpisodesViewModel::class.java

    override var layoutId: Int = R.layout.fragment_episodes

    override val onClickListenerManager: BaseViewHolder.IItemOnClickListener<Episode>
        get() = this

    override val items: MutableList<Episode>
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
        fragment_episodes_recyclerview?.let {

            val adapter = EpisodesRecyclerViewAdapters()
            adapter.manager = this

            it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            it.adapter = adapter

        }
        fragment_episodes_swipe.setOnRefreshListener {
            viewModel.loadData()
        }
    }

    override fun initViewModelObserver() {
        super.initViewModelObserver()
        viewModel.mItems.observe(this, Observer {
            fragment_episodes_swipe.isRefreshing = false
            fragment_episodes_recyclerview?.adapter?.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance() = EpisodesFragment()
    }

    //endregion

    //region RecyclerView Manager Methods

    override fun numberOfItem(): Int = items.size

    override fun getItemAtPosition(position: Int): Episode = items[position]

    //endregion

    //region ItemClick Delegate Methods

    override var onClickBlock: ClickHandler<Episode> = { _, _ ->
        //  Nothing to do here for the moment
    }

    //endregion

    //region Loading management

    override fun startLoading() {
        fragment_episodes_errorview.visibility = View.GONE
        fragment_episodes_progressbar.visibility = View.VISIBLE
        fragment_episodes_loading_group.visibility = View.GONE
    }

    override fun stopLoading() {
        fragment_episodes_swipe.isRefreshing = false
        fragment_episodes_progressbar.visibility = View.GONE
        fragment_episodes_loading_group.visibility = View.VISIBLE
    }

    //endregion

    //region Error Management

    override fun onError(err: Throwable) {
        err.message?.let {
            fragment_episodes_errorview.title = it
        } ?: run {
            fragment_episodes_errorview.title = getString(R.string.error_default)
        }
        fragment_episodes_errorview.visibility = View.VISIBLE
    }

    //endregion
}
