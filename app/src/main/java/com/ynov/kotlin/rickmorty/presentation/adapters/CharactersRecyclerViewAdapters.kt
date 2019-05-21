package com.ynov.kotlin.rickmorty.presentation.adapters

import android.view.ViewGroup
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.CharacterViewHolder

class CharactersRecyclerViewAdapters: BaseRecyclerViewAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        inflateViewFromLayout(parent, R.layout.cell_character)?.let {
            return CharacterViewHolder(it)
        }
        return CharacterViewHolder(parent.rootView)
    }

}