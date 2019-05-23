package com.ynov.kotlin.rickmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder

abstract class BaseRecyclerViewAdapter<T>: RecyclerView.Adapter<BaseViewHolder<T>>() {

    interface IRecyclerViewManager<T> {
        val items: MutableList<T>
        val onClickListenerManager: BaseViewHolder.IItemOnClickListener<T>
        fun numberOfItem(): Int
        fun getItemAtPosition(position: Int): T
    }

    var manager: IRecyclerViewManager<T>? = null

    private val nbOfItems: Int
        get() = manager?.numberOfItem() ?: 0

    override fun getItemCount(): Int {
        return nbOfItems
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType:Int): BaseViewHolder<T> {
        return createNewViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        manager?.let { mgr ->
            val currentObject = mgr.getItemAtPosition(position)
            currentObject.apply {
                holder.itemOnClickListenerManager = mgr.onClickListenerManager
                holder.layoutForObject(this, position)
            }
        }
    }

    fun inflateViewFromLayout(parent: ViewGroup?, layoutId:Int): View? {
        parent?.let {
            return LayoutInflater.from(it.context).inflate(layoutId, parent, false)
        }
        return null
    }

    abstract fun createNewViewHolder(@NonNull parent: ViewGroup, viewType:Int): BaseViewHolder<T>

}