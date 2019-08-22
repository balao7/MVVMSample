package com.android.mvvm.sample.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.mvvm.sample.R
import com.android.mvvm.sample.databinding.ActivityMainBinding
import com.android.mvvm.sample.view.main.fragments.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isNewInstanceState: Boolean = savedInstanceState == null
        val homeDataBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        
        homeDataBinding?.let {
            it.viewmodel = this.homeViewModel
            it.mainBottomNavView.setOnNavigationItemReselectedListener {menuItem ->
                this.loadFragment(menuItem.itemId)
            }

            if (isNewInstanceState) {
                it.mainBottomNavView.selectedItemId = R.id.home
            }
        }
    }

    /**
     * Load Fragment when user clicks on Bottom Navigation Menu.
     * @param itemId {Int}
     */
    private fun loadFragment(itemId: Int) {
        val tag: String = itemId.toString()
        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.home -> HomeFragment.newInstance()
            R.id.activities -> HomeFragment.newInstance()
            R.id.resources -> HomeFragment.newInstance()
            R.id.shop -> HomeFragment.newInstance()

            else -> null
        }

        fragment?.let {
            this.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, it, tag)
                .commit()
        }
    }
}
