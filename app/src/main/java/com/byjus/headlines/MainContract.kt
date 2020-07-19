package com.byjus.headlines

import com.byjus.headlines.di.pojo.News

public interface MainContract {

    interface ViewCallBack {
        fun showProgress(news: News)

        fun hideProgress()

    }

    interface ModelCallBack {
        interface OnFinishedListener {
            fun onFinished(string:String)
        }

        fun getData(onFinishedListener:OnFinishedListener)
    }

    interface PresenterCallBack {
        fun loadData()
    }
}