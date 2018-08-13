package com.bitvale.droidmotion.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bitvale.droidmotion.model.DataProvider.BaseData
import com.bitvale.droidmotion.model.DataProvider.Details
import com.bitvale.droidmotion.adapter.BaseViewHolder.DetailsViewHolder
import com.bitvale.droidmotion.adapter.BaseViewHolder.CardViewHolder


/**
 * Created by Alexander Kolpakov on 17.07.2018
 */
class RecyclerAdapter<T : BaseData>(private var dataSet: List<T>, private var listener: View.OnClickListener? = null)
    : RecyclerView.Adapter<BaseViewHolder<T>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return when (viewType) {
            0 -> DetailsViewHolder(parent) as BaseViewHolder<T>
            else -> CardViewHolder(parent) as BaseViewHolder<T>
        }
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int {
        val type = dataSet[0].javaClass
        return when (type) {
            Details::class.java -> 0
            else -> 1
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) = holder.bind(dataSet[position], listener)
}