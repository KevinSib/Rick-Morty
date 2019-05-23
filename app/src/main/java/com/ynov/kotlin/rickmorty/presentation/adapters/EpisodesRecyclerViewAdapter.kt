package com.ynov.kotlin.rickmorty.presentation.adapters

import android.view.ViewGroup
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.EpisodeViewHolder

class EpisodesRecyclerViewAdapters: BaseRecyclerViewAdapter<Episode>() {

    override fun createNewViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Episode> {
        inflateViewFromLayout(parent, R.layout.cell_episode)?.let {
            return EpisodeViewHolder(it)
        }
        return EpisodeViewHolder(parent.rootView)
    }

}