package com.byjus.headlines

import com.byjus.headlines.di.database.Article
import com.byjus.headlines.di.database.LocalDatabase
import com.byjus.headlines.di.pojo.Articles
import com.byjus.headlines.di.pojo.News
import com.byjus.headlines.di.retrofit.GetTopNewsInterFace
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject
typealias ArticleLambda = (List<Article>) -> Unit
class PresenterImpl @Inject constructor(val getTopNewsInterFace: GetTopNewsInterFace,val mView:MainContract.ViewCallBack) :MainContract.PresenterCallBack {
    lateinit var db:LocalDatabase
    override fun loadData(localDatabase: LocalDatabase) {
        db=localDatabase
        deleterecords()
        println("hello")
        mView.showProgress()
        getTopNewsInterFace.getnews("US","business","7851dde50bc345b4979c5b6dcec07f7e")
         .subscribeOn(rx.schedulers.Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(object : Observer<News?> {
                    override fun onCompleted() {
                        mView.hideProgress()
                    }

                    override fun onError(e: Throwable) {
                        mView.hideProgress()
                    }

                    override fun onNext(data: News?) {
                        if (data != null) {
                            data.articles.map {
                                insertrecord(it)
                            }
                        }
                        getAllrecord(db)
                    }
                })

    }
    fun deleterecords()
    {
        val thread = Thread {
            db.NewsDescriptionDao().nukeTable()
        }
        thread.start()
    }
    fun insertrecord(newsarticle: Articles)
    {
        val thread = Thread {

            var article=Article(newsarticle.title,newsarticle.description,newsarticle.urlToImage,newsarticle.publishedAt?.substring(0,10),newsarticle.source?.name)
            db.NewsDescriptionDao().insert(article)

        }
        thread.start()
    }
    override fun getAllrecord(db:LocalDatabase)
    {
        this.db=db
        println(db.NewsDescriptionDao().getDescriptions())
        getDescriptions{
            mView.hideProgress()
             mView.showComplete(it)
           }
        }

    private fun getDescriptions(finished: ArticleLambda) {
        db.NewsDescriptionDao()
                .getDescriptions()
                .subscribeOn(Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(finished)
    }

}


