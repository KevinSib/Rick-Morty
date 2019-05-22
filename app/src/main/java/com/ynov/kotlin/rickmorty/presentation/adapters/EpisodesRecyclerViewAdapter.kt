package com.ynov.kotlin.rickmorty.presentation.adapters

import android.view.ViewGroup
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.CharacterViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.EpisodeViewHolder

class EpisodesRecyclerViewAdapters: BaseRecyclerViewAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        inflateViewFromLayout(parent, R.layout.cell_episode)?.let {
            return EpisodeViewHolder(it)
        }
        return EpisodeViewHolder(parent.rootView)
    }

}