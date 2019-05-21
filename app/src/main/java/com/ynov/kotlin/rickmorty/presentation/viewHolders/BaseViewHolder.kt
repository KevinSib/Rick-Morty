package com.ynov.kotlin.rickmorty.presentation.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface IItemOnClickListener {
        fun OnClickRecyclerViewItem(obj: Any, atPosition: Int)
    }

    private var itemOnClickListenerManager: IItemOnClickListener? = null

    fun setItemOnClickListenerManager(listener: IItemOnClickListener) {
        this.itemOnClickListenerManager = listener
    }

    open fun layoutForObject(obj: Any, atPosition: Int) {
        itemView.setOnClickListener {
            itemOnClickListenerManager?.OnClickRecyclerViewItem(obj, atPosition)
        }
    }

}