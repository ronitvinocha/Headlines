package com.byjus.headlines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.byjus.headlines.di.component.DaggerMainActivityComponent
import com.byjus.headlines.di.component.MainActivityComponent
import com.byjus.headlines.di.module.ActivityContextModule
import com.byjus.headlines.di.module.MvpModule
import com.byjus.headlines.di.pojo.News
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),MainContract.ViewCallBack,RecyclerViewAdapter.ClickListener {
    lateinit var mainActivityComponent: MainActivityComponent
    @Inject
    lateinit var presenterImpl: PresenterImpl
     @Inject
    lateinit var  recyclerViewAdapter:RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         var w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setSupportActionBar(my_toolbar)
        val applicationComponent=MyApplication.get(this).getapplicationComponent()
        mainActivityComponent= DaggerMainActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .activityContextModule(ActivityContextModule(this)).
            mvpModule(MvpModule(this)).build()
        mainActivityComponent.inject(this)
        presenterImpl.loadData()
        var context=mainActivityComponent.getContext()
        println(context)
        var mContext=applicationComponent.getContext()
        println(mContext)
        println("😄"+recyclerViewAdapter)
        recyclerview.adapter=recyclerViewAdapter
//          recyclerview.adapter=recyclerViewAdapter
    }


    override fun showProgress() {
        progressbar.visibility= View.VISIBLE
        recyclerview.visibility=View.GONE
    }


    override fun hideProgress() {
        progressbar.visibility= View.GONE
        recyclerview.visibility=View.VISIBLE
    }

    override fun showComplete(news: News) {
       println(news.articles.size)
        news.articles.map {
           it-> println(it.urlToImage)
        }
      recyclerViewAdapter.setData(news.articles)
        println("hello"+recyclerViewAdapter)

    }

    override fun launchIntent(name: String) {
        TODO("Not yet implemented")
    }

}