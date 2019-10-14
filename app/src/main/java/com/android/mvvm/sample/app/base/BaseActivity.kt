/*
 * Copyright (c) 2019 3iOptimus. All Rights Reserved.
 *
 * Created by balagovind on 2/10/19 12:41 AM.
 * Last modified 2/10/19 12:41 AM and by balagovind.
 *
 */

package com.a3i.android.eventzapp.features.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutResID: Int) :
    DaggerAppCompatActivity() {

    protected lateinit var dataBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.dataBinding = DataBindingUtil.setContentView(this, layoutResID)
    }
}
