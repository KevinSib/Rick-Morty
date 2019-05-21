package com.ynov.kotlin.rickmorty.presentation.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.entity.Character
import com.ynov.kotlin.rickmorty.presentation.widgets.SquareImageView

class CharacterViewHolder(itemView: View) : BaseViewHolder(itemView) {

    override fun layoutForObject(obj: Any, atPosition: Int) {
        super.layoutForObject(obj, atPosition);
        if (obj is Character) {
            var nameTextView = itemView.findViewById<TextView>(R.id.cell_character_name)
            nameTextView.text = obj.name
            var picImageView = itemView.findViewById<SquareImageView>(R.id.cell_character_picture)
            Picasso
                .get()
                .load(obj.image)
                .into(picImageView)
        }
    }

}