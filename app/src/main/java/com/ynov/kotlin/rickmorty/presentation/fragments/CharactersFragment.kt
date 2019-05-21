package com.ynov.kotlin.rickmorty.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.ApiManager
import com.ynov.kotlin.rickmorty.data.DataRepository
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.remote.CharacterResult
import com.ynov.kotlin.rickmorty.presentation.adapters.BaseRecyclerViewAdapter.IRecyclerViewManager
import com.ynov.kotlin.rickmorty.presentation.adapters.CharactersRecyclerViewAdapters
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CharactersFragment : Fragment(), IRecyclerViewManager, BaseViewHolder.IItemOnClickListener {

    var mItems: MutableList<Character> = mutableListOf()
    var mRecyclerView: RecyclerView? = null

    override val onClickListenerManager: BaseViewHolder.IItemOnClickListener
        get() = this

    override val items: MutableList<Any>
        get() = mItems as MutableList<Any>

    //region Default Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            it.layoutManager = GridLayoutManager(context, 3)
            it.adapter = adapter

        }

        val apiManager: ApiManager = ApiManager()
        var characterResult: Single<CharacterResult> = DataRepository(apiManager).RetrieveCaracter()

        var a =  characterResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                mItems = res.results.toMutableList()
                mRecyclerView?.adapter?.notifyDataSetChanged()
            })

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
    }

    //endregion
}
