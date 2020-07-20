package com.byjus.headlines

import com.byjus.headlines.di.database.Article
import com.byjus.headlines.di.database.LocalDatabase
import com.byjus.headlines.di.pojo.News

public interface MainContract {

    interface ViewCallBack {
        fun showProgress()

        fun hideProgress()

        fun showComplete(articles: List<Article>)



    }

    interface ModelCallBack {
        interface OnFinishedListener {
            fun onFinished(string:String)
        }

        fun getData(onFinishedListener:OnFinishedListener)
    }

    interface PresenterCallBack {
        fun loadData(localDatabase: LocalDatabase)
        fun getAllrecord(db:LocalDatabase)
    }
}