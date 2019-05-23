package com.ynov.kotlin.rickmorty.presentation.adapters

import android.view.ViewGroup
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.CharacterViewHolder

class CharactersRecyclerViewAdapters: BaseRecyclerViewAdapter<Character>() {

    override fun createNewViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Character> {
        inflateViewFromLayout(parent, R.layout.cell_character)?.let {
            return CharacterViewHolder(it)
        }
        return CharacterViewHolder(parent.rootView)
    }

}