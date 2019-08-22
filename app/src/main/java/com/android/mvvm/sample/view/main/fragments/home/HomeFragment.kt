package com.android.mvvm.sample.view.main.fragments.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.android.mvvm.sample.R
import com.android.mvvm.sample.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewmodel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
    private var homeFragmentBinding: HomeFragmentBinding? = null
    private val homeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.homeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return this.homeFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.homeFragmentBinding?.let {
            it.viewmodel = viewmodel
            it.homeAdapter = homeAdapter

            this.viewmodel.startLoadHomeListHandler()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.viewmodel.stopLoadHomeListHandler()
    }
}
