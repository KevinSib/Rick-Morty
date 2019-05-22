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
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.activities.CharacterDetailActivity
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter.IRecyclerViewManager
import com.ynov.kotlin.rickmorty.presentation.adapters.CharactersRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharactersViewModel


class CharactersFragment : Fragment(), IRecyclerViewManager, BaseViewHolder.IItemOnClickListener {

    private var mRecyclerView: RecyclerView? = null

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProviders.of(this).get(CharactersViewModel::class.java)
    }

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

    private val itemChanged = Observer<MutableList<Character>> { value ->
        mRecyclerView?.adapter?.notifyDataSetChanged()
    }

    //region Default Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        mRecyclerView = view.findViewById(R.id.fragment_characters_recyclerview)
        return view
    }

    override fun onStart() {

        super.onStart()

        mRecyclerView?.let {

            val adapter = CharactersRecyclerViewAdapters()
            adapter.manager = this

            it.layoutManager = GridLayoutManager(context, 2)
            it.adapter = adapter

        }

        viewModel.mItems.observe(this, itemChanged)

    }

    companion object {
        fun newInstance() = CharactersFragment()
    }

    //endregion

    //region RecyclerView Manager Methods

    override fun numberOfItem(): Int = items.size

    override fun getItemAtPosition(position: Int): Any = items[position]

    //endregion

    //region ItemClick Delegate Methods

    override fun onClickRecyclerViewItem(obj: Any, atPosition: Int) {
        if (obj is Character) {
            val newIntent = CharacterDetailActivity.newIntent(requireContext(), obj.id)
            startActivity(newIntent)
        }
    }

    //endregion
}
