package com.ynov.kotlin.rickmorty.presentation.viewHolders

import android.view.View
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.data.entity.Episode
import com.ynov.kotlin.rickmorty.presentation.widgets.SquareImageView

class EpisodeViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun layoutForObject(obj: Any, atPosition: Int) {
        super.layoutForObject(obj, atPosition);
        if (obj is Episode) {
            var nameTextView = itemView.findViewById<TextView>(R.id.cell_episode_name)
            nameTextView.text = obj.name
            var episodeTextView = itemView.findViewById<TextView>(R.id.cell_episode_episode)
            episodeTextView.text = obj.episode
        }
    }

}