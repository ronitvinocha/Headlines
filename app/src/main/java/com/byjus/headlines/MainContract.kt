package com.byjus.headlines

import com.byjus.headlines.di.pojo.News

public interface MainContract {

    interface ViewCallBack {
        fun showProgress()

        fun hideProgress()

        fun showComplete(news: News)

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