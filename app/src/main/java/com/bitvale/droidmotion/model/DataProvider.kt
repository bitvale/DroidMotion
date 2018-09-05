package com.bitvale.droidmotion.model

import androidx.annotation.DrawableRes
import com.bitvale.androidmotion.R

/**
 * Created by Alexander Kolpakov on 24.07.2018
 */
object DataProvider {

    fun getCardData(): List<Card> {
        val data = ArrayList<Card>()
        data.add(Card(0, "Google Play", "28.07.2018", "$38,456.78", Status.COMPLAINT, R.drawable.img_google_play))
        data.add(Card(1, "Twitter", "27.07.2018", "$1,550.60", Status.RECEIVED, R.drawable.img_twitter))
        data.add(Card(2, "YouTube", "27.07.2018", "$14,340.00", Status.CORRECT, R.drawable.img_youtube))
        data.add(Card(3, "Dribbble", "26.07.2018", "$2,678.27", Status.ERROR, R.drawable.img_dribble))
        data.add(Card(4, "Apple Store", "26.07.2018", "$20,479.12", Status.CORRECT, R.drawable.img_apple))
        data.add(Card(5, "VK", "25.07.2018", "$13,846.13", Status.INCORRECT, R.drawable.img_vk))
        data.add(Card(6, "Instagram", "25.07.2018", "$24,856.17", Status.NOT_RECEIVED, R.drawable.img_instagram))
        data.add(Card(7, "Github", "24.07.2018", "$376.90", Status.COMPLAINT, R.drawable.img_github))
        data.add(Card(8, "Vimeo", "23.07.2018", "$7,568.02", Status.RECEIVED, R.drawable.img_vimeo))
        data.add(Card(9, "Facebook", "10.07.2018", "$18,347.32", Status.INCORRECT, R.drawable.img_facebook))
        return data
    }

    fun getDetailsData(): List<Details> {
        val data = ArrayList<Details>()
        data.add(Details(0, "In App purchase\"Gem\"", "$14,340.00 X1 (including VAT 10%)", "$14,340.00"))
        data.add(Details(0, "In App purchase \"Money\"", "$2,456.78 X1 (including VAT 10%)", "$2,456.78"))
        data.add(Details(0, "Interstitial Ads", "$1,150.15 X1 (including VAT 10%)", "$1,150.15"))
        data.add(Details(0, "Rewarded video", "$566.20 X1 (including VAT 10%)", "$566.20"))
        return data
    }

    interface BaseData

    data class Card(val id: Int, val name: String, val date: String, val amount: String, val status: Status,
                    @DrawableRes val imageId: Int) : BaseData

    data class Details(val id: Int, val title: String, val subtitle: String, val amount: String) : BaseData

    enum class Status(val code: String, @DrawableRes val iconId: Int) {
        CORRECT("Correct", R.drawable.ic_correct),
        INCORRECT("Incorrect", R.drawable.ic_incorrect),
        COMPLAINT("Complaint", R.drawable.ic_complaint),
        ERROR("Error", R.drawable.ic_error),
        RECEIVED("Received", R.drawable.ic_received),
        NOT_RECEIVED("Not received", R.drawable.ic_not_received)
    }
}