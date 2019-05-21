package com.ynov.kotlin.rickmorty.presentation.viewHolders

import android.view.View
import android.widget.TextView
import com.ynov.kotlin.rickmorty.R

class CharacterViewHolder(itemView: View) : BaseViewHolder(itemView) {



    override fun layoutForObject(obj: Any, atPosition: Int) {
        super.layoutForObject(obj, atPosition);
        //  TODO get character fron any value
        //  TODO update cell for character
        var nameTextView = itemView.findViewById<TextView>(R.id.cell_character_name)
        nameTextView.text = "Toto"
    }

}