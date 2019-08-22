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
    BindableAdapter<HomeData> {

    private var homeListData: List<HomeData> = listOf(
        HomeData(
            "Valid up to 2020",
            "Asset holder for marketing",
            "$20",
            "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
        ),
        HomeData(
            "",
            "Asset holder for managers",
            "$25",
            "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
        ),
        HomeData(
            "",
            "Asset holder for ceo's",
            "$50",
            "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
        ),
        HomeData(
            "",
            "Asset holder for clerks",
            "$10",
            "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
        ),
        HomeData(
            "",
            "Asset holder for IT dept",
            "$10",
            "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
        )
    )

    override fun setData(data: List<HomeData>) {
        this.homeListData = data
        this.notifyDataSetChanged()
    }

    override fun changePosition(positions: Set<Int>) =
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
        return this.homeListData.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.home_card_item
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeListData[position])
    }

    class HomeViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(homeData: HomeData) {
            this.viewDataBinding.setVariable(BR.homedata, homeData)
            this.viewDataBinding.executePendingBindings()
        }
    }

}