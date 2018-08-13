package com.bitvale.droidmotion

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bitvale.androidmotion.R
import com.bitvale.droidmotion.common.replaceFragmentInActivity
import com.bitvale.droidmotion.fragment.RecyclerFragment
import com.bitvale.droidmotion.listener.BottomNavigationViewListener
import com.bitvale.droidmotion.listener.OnBackPressedListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Alexander Kolpakov on 25.07.2018
 */
class MainActivity : AppCompatActivity(), BottomNavigationViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment(RecyclerFragment.newInstance())
    }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager.findFragmentById(R.id.fragment_container)
                ?: fragment.let {
                    replaceFragmentInActivity(it, R.id.fragment_container)
                }
    }

    override fun hideBottomNavigationView() {
        if (bottom_navigation.translationY == 0f)
            bottom_navigation.animate()
                    .translationY(bottom_navigation.height.toFloat())
                    .setDuration(250)
                    .start()
    }

    override fun showBottomNavigationView() {
        if (bottom_navigation.translationY >= bottom_navigation.height.toFloat())
            bottom_navigation.animate()
                    .translationY(0f)
                    .setDuration(400)
                    .start()
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        var proceedToSuper = true
        for (fragment in fragmentList) {
            if (fragment is OnBackPressedListener) {
                proceedToSuper = false
                (fragment as OnBackPressedListener).onBackPressed()
            }
        }
        if (proceedToSuper) super.onBackPressed()
    }
}
