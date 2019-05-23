package com.ynov.kotlin.rickmorty.presentation.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

typealias ClickHandler<T> = (T, Int) -> Unit

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface IItemOnClickListener<T> {
        var onClickBlock: ClickHandler<T>
    }

    var itemOnClickListenerManager: IItemOnClickListener<T>? = null

    open fun layoutForObject(obj: T, atPosition: Int) {
        itemOnClickListenerManager?.let { mgr ->
            itemView.setOnClickListener {
                mgr.onClickBlock(obj, atPosition)
            }
        }

    }

}