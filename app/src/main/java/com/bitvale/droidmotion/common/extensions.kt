package com.bitvale.droidmotion.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bitvale.androidmotion.R

/**
 * Created by Alexander Kolpakov on 17.07.2018
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.onLayoutAction(action: () -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            removeOnLayoutChangeListener(this)
            action()
        }
    })
}

fun Context.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else 0
}

fun Context.getToolbarHeight(): Int {
    val tv = TypedValue()
    return if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true))
        TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    else 0
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun View.copyViewImage(): View {
    val copy = ImageView(context)

    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)

    copy.setImageBitmap(bitmap)

    // On preLollipop when we create copy card view's shadow is copied too as content and
    // we do not need additional card view.

    return (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        CardView(context).apply {
            cardElevation = resources.getDimension(R.dimen.card_elevation)
            radius = resources.getDimension(R.dimen.card_corner_radius)
            addView(copy)
        }
    } else {
        copy
    }).apply {
        layoutParams = this@copyViewImage.layoutParams
        layoutParams.height = this@copyViewImage.height
        layoutParams.width = this@copyViewImage.width
        x = this@copyViewImage.x
        y = this@copyViewImage.y
    }
}

fun Animator.withEndAction(action: () -> Unit): Animator {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            action()
        }
    })
    return this
}

fun Animator.withStartAction(action: () -> Unit): Animator {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            action()
        }
    })
    return this
}

inline fun supportsLollipop(action: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        action()
    }
}