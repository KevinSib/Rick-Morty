package com.ynov.kotlin.rickmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.presentation.viewHolders.BaseViewHolder
import com.ynov.kotlin.rickmorty.presentation.viewHolders.CharacterViewHolder

abstract class BaseRecyclerViewAdapter: RecyclerView.Adapter<BaseViewHolder>() {

    interface IRecyclerViewManager {
        val items: List<Any>
        val onClickListenerManager: BaseViewHolder.IItemOnClickListener
        fun numberOfItem(): Int
        fun getItemAtPosition(position: Int): Any
    }

    var manager: IRecyclerViewManager? = null

    val nbOfItems: Int
        get() = manager?.numberOfItem() ?: 0

    override fun getItemCount(): Int {
        return nbOfItems
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType:Int): BaseViewHolder {
        //  to be suclassed
        return CharacterViewHolder(View(parent.context))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (manager == null) {
            return
        }
        val currentObject = manager!!.getItemAtPosition(position)
        if (currentObject != null && holder is BaseViewHolder) {
            holder.setItemOnClickListenerManager(manager!!.onClickListenerManager)
            holder.layoutForObject(currentObject, position)
        }
    }

    fun inflateViewFromLayout(parent: ViewGroup?, layoutId:Int): View? {
        return if (parent == null) {
            null
        } else LayoutInflater.from(parent!!.context).inflate(layoutId, parent, false)
    }

}