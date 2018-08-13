package com.bitvale.droidmotion.adapter

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bitvale.androidmotion.R
import com.bitvale.droidmotion.common.inflate
import com.bitvale.droidmotion.model.DataProvider.BaseData
import com.bitvale.droidmotion.model.DataProvider.Card
import com.bitvale.droidmotion.model.DataProvider.Details
import com.bitvale.droidmotion.common.TRANSITION_CARD
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_card.*
import kotlinx.android.synthetic.main.item_details.*

/**
 * Created by Alexander Kolpakov on 29.07.2018
 */
abstract class BaseViewHolder<T : BaseData>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

    override val containerView: View?
        get() = itemView

    abstract fun bind(data: T, listener: View.OnClickListener? = null)


    class CardViewHolder(parent: ViewGroup) : BaseViewHolder<Card>(parent.inflate(R.layout.item_card)) {

        override fun bind(data: Card, listener: View.OnClickListener?) {
            containerView?.setOnClickListener(listener)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                containerView?.transitionName = TRANSITION_CARD + adapterPosition
            }

            tv_title.text = data.name
            tv_amount.text = data.amount
            tv_date.text = data.date
            tv_status.text = data.status.code
            img_status.setImageResource(data.status.iconId)
            img_card.setImageResource(data.imageId)
        }
    }

    class DetailsViewHolder(parent: ViewGroup) : BaseViewHolder<Details>(parent.inflate(R.layout.item_details)) {

        override fun bind(data: Details, listener: View.OnClickListener?) {
            tv_details_title.text = data.title
            tv_details_subtitle.text = data.subtitle
            tv_details_amount.text = data.amount
        }

    }
}


