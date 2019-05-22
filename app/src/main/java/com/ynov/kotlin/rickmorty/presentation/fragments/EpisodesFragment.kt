package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter
import com.ynov.kotlin.rickmorty.presentation.adapters.CharactersRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.adapters.EpisodesRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewModels.EpisodesViewModel
import kotlinx.android.synthetic.main.fragment_episodes.*

class EpisodesFragment : BaseFragment<EpisodesViewModel>(),
    BaseRecyclerViewAdapter.IRecyclerViewManager,
    BaseViewHolder.IItemOnClickListener {

    override val viewModelClass = EpisodesViewModel::class.java

    override var layoutId: Int = R.layout.fragment_episodes

    override val onClickListenerManager: BaseViewHolder.IItemOnClickListener
        get() = this

    override val items: MutableList<Any>
        get() {
            viewModel.let {
                it.mItems.value?.let { it ->
                    return it as MutableList<Any>
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
        viewModel.mItems.observe(this, Observer {
            fragment_episodes_swipe.isRefreshing = false
            fragment_episodes_recyclerview?.adapter?.notifyDataSetChanged()
        })
        viewModel.mIsLoading.observe(this, Observer {
            if (it)
                startLoading()
            else
                stopLoading()
        })
    }

    companion object {
        fun newInstance() = EpisodesFragment()
    }

    //endregion

    //region RecyclerView Manager Methods

    override fun numberOfItem(): Int = items.size

    override fun getItemAtPosition(position: Int): Any = items[position]

    //endregion

    //region ItemClick Delegate Methods

    override fun onClickRecyclerViewItem(obj: Any, atPosition: Int) {
        //  TODO Implement click if needed
    }

    //endregion

    //region Loading management

    fun startLoading() {
        fragment_episodes_progressbar.visibility = View.VISIBLE
        fragment_episodes_loading_group.visibility = View.GONE
    }

    fun stopLoading() {
        fragment_episodes_progressbar.visibility = View.GONE
        fragment_episodes_loading_group.visibility = View.VISIBLE
    }

    //endregion
}
