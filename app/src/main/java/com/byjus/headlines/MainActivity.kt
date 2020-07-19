package com.byjus.headlines

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.byjus.headlines.di.component.ApplicationComponent
import com.byjus.headlines.di.component.DaggerMainActivityComponent
import com.byjus.headlines.di.component.MainActivityComponent
import com.byjus.headlines.di.module.ActivityContextModule
import com.byjus.headlines.di.module.MvpModule
import com.byjus.headlines.di.pojo.News
import com.byjus.headlines.di.qualifiers.ActivityContext
import com.byjus.headlines.di.qualifiers.ApplicationContext
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
    }

    override fun showProgress(news: News) {

    }


    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun launchIntent(name: String) {
        TODO("Not yet implemented")
    }
}