package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.DataRepository
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter.IRecyclerViewManager
import com.ynov.kotlin.rickmorty.presentation.adapters.CharactersRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewModels.CharactersViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CharactersFragment : Fragment(), IRecyclerViewManager, BaseViewHolder.IItemOnClickListener {

    var mRecyclerView: RecyclerView? = null

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProviders.of(this).get(CharactersViewModel::class.java)
    }

    override val onClickListenerManager: BaseViewHolder.IItemOnClickListener
        get() = this

    override val items: MutableList<Any>
        get() {
            viewModel?.let {
                it.mItems.value?.let {
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
        mRecyclerView = view.findViewById<RecyclerView>(R.id.fragment_characters_recyclerview)
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
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }

    //endregion

    //region RecyclerView Manager Methods

    override fun numberOfItem(): Int = items.size

    override fun getItemAtPosition(position: Int): Any = items.get(position)

    //endregion

    //region ItemClick Delegate Methods

    override fun OnClickRecyclerViewItem(obj: Any, atPosition: Int) {
        //  TODO React to item clicks
        print("click on item !")
    }

    //endregion
}
