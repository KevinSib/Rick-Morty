package com.ynov.kotlin.rickmorty.presentation.viewHolders

import android.view.View
import android.widget.TextView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Episode

class EpisodeViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun layoutForObject(obj: Any, atPosition: Int) {
        super.layoutForObject(obj, atPosition)
        if (obj is Episode) {
            val nameTextView = itemView.findViewById<TextView>(R.id.cell_episode_name)
            nameTextView.text = obj.name
            val episodeTextView = itemView.findViewById<TextView>(R.id.cell_episode_episode)
            episodeTextView.text = obj.episode
        }
    }

}