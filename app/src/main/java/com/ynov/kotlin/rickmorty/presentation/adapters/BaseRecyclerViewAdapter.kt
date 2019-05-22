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
        val items: MutableList<Any>
        val onClickListenerManager: BaseViewHolder.IItemOnClickListener
        fun numberOfItem(): Int
        fun getItemAtPosition(position: Int): Any
    }

    var manager: IRecyclerViewManager? = null

    private val nbOfItems: Int
        get() = manager?.numberOfItem() ?: 0

    override fun getItemCount(): Int {
        return nbOfItems
    }

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType:Int): BaseViewHolder {
        //  to be suclassed
        return CharacterViewHolder(View(parent.context))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        manager?.let {
            val currentObject = it.getItemAtPosition(position)
            currentObject.let {
                holder.setItemOnClickListenerManager(manager!!.onClickListenerManager)
                holder.layoutForObject(currentObject, position)
            }
        }
    }

    fun inflateViewFromLayout(parent: ViewGroup?, layoutId:Int): View? {
        parent?.let {
            return LayoutInflater.from(it.context).inflate(layoutId, parent, false)
        }
        return null
    }

}