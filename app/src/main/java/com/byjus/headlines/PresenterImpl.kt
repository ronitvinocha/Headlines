package com.byjus.headlines

import com.byjus.headlines.di.pojo.News
import com.byjus.headlines.di.retrofit.GetTopNewsInterFace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class PresenterImpl @Inject constructor(val getTopNewsInterFace: GetTopNewsInterFace,val mView:MainContract.ViewCallBack) :MainContract.PresenterCallBack {

    override fun loadData() {
        println("hello")
     val call= getTopNewsInterFace.getnews("US","business","7851dde50bc345b4979c5b6dcec07f7e")
            call.enqueue(object:Callback<News>{
                override fun onFailure(call: Call<News>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    println(response.body())
                    response.body()?.let { mView.showProgress(it) }
                }

            })

    }

}