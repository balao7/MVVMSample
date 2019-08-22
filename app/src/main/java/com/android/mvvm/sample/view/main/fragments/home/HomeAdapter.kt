package com.android.mvvm.sample.view.main.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.mvvm.sample.BR
import com.android.mvvm.sample.R
import com.android.mvvm.sample.common.BindableAdapter

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(),
    BindableAdapter<HomeListData> {

    private var homeListListData: List<HomeListData> = emptyList()

    override fun setListData(list: List<HomeListData>?) {
        list?.let {
            this.homeListListData = it
            notifyDataSetChanged()
        }
    }

    override fun changePositions(positions: Set<Int>) =
        positions.forEach { this.notifyItemChanged(it) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewDataBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, viewType, parent, false)

        return HomeViewHolder(viewDataBinding)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return this.homeListListData.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.home_card_item
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeListListData[position])
    }

    class HomeViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(homeListData: HomeListData) {
            this.viewDataBinding.setVariable(BR.homeListData, homeListData)
            this.viewDataBinding.executePendingBindings()
        }
    }

}