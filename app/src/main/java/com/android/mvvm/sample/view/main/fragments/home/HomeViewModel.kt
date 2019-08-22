package com.android.mvvm.sample.view.main.fragments.home

import android.os.Handler
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val homeDataModel = HomeDataModel()
    private val updateInterval = 1000L
    private val loadHomeListHandler = Handler()

    fun startLoadHomeListHandler() {
        this.loadHomeListHandler.postDelayed(this.loadHomeListRunnable, this.updateInterval)
    }

    fun stopLoadHomeListHandler() {
        this.loadHomeListHandler.removeCallbacks(this.loadHomeListRunnable)
    }

    private var loadHomeListRunnable: Runnable = object : Runnable {
        override fun run() {
            loadHomeListData()
            loadHomeListHandler.postDelayed(this, updateInterval)
        }
    }

    fun loadHomeListData() {
        val homeListData: List<HomeListData> = listOf(
            HomeListData(
                "Valid up to 2020",
                "Asset holder for marketing",
                "$20",
                "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
            ),
            HomeListData(
                "",
                "Asset holder for managers",
                "$25",
                "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
            ),
            HomeListData(
                "",
                "Asset holder for ceo's",
                "$50",
                "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
            ),
            HomeListData(
                "",
                "Asset holder for clerks",
                "$10",
                "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
            ),
            HomeListData(
                "",
                "Asset holder for IT dept",
                "$10",
                "https://cititel.com/wp-content/uploads/2016/03/Banner-Medical-Answering-Services-1.png"
            )
        )

        this.homeDataModel.homeDataList = homeListData.toMutableList()
    }

    fun getHomeDataModel(): HomeDataModel {
        return this.homeDataModel
    }

}
