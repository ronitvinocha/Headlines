package com.byjus.headlines

import com.byjus.headlines.di.pojo.News
import com.byjus.headlines.di.retrofit.GetTopNewsInterFace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Observer
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class PresenterImpl @Inject constructor(val getTopNewsInterFace: GetTopNewsInterFace,val mView:MainContract.ViewCallBack) :MainContract.PresenterCallBack {

    override fun loadData() {
        println("hello")
        mView.showProgress()
        getTopNewsInterFace.getnews("US","business","7851dde50bc345b4979c5b6dcec07f7e")
         .subscribeOn(Schedulers.io())
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
                            mView.showComplete(data)
                        }
                    }
                })

    }

}