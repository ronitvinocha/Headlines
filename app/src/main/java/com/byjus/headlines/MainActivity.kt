package com.byjus.headlines

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.byjus.headlines.di.component.DaggerMainActivityComponent
import com.byjus.headlines.di.component.MainActivityComponent
import com.byjus.headlines.di.database.Article
import com.byjus.headlines.di.database.LocalDatabase
import com.byjus.headlines.di.module.ActivityContextModule
import com.byjus.headlines.di.module.MvpModule
import com.byjus.headlines.di.pojo.Articles
import com.byjus.headlines.di.pojo.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),MainContract.ViewCallBack,RecyclerViewAdapter.ClickListener {
    lateinit var mainActivityComponent: MainActivityComponent
    @Inject
    lateinit var presenterImpl: PresenterImpl
     @Inject
    lateinit var  recyclerViewAdapter:RecyclerViewAdapter
    lateinit var picasso: Picasso
    lateinit var db:LocalDatabase

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
        picasso=applicationComponent.getPiccaso()
        println(picasso)
        db=applicationComponent.getDatabase()
        recyclerViewAdapter.setPiccaso(picasso)
        recyclerview.adapter=recyclerViewAdapter
        if(isNetworkConnected())
        {
            presenterImpl.loadData(db)
        }
        else
        {
            presenterImpl.getAllrecord(db)
        }
    }
    private fun isNetworkConnected(): Boolean {
      val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val activeNetwork = connectivityManager.activeNetwork
      val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
      return networkCapabilities != null &&
          networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun showProgress() {
        progressbar.visibility= View.VISIBLE
        recyclerview.visibility=View.GONE
    }


    override fun hideProgress() {
        progressbar.visibility= View.GONE
        recyclerview.visibility=View.VISIBLE
    }

    override fun showComplete(articles:List<Article>) {
      recyclerViewAdapter.setData(articles)
        println("hello"+recyclerViewAdapter)

    }

    override fun launchIntent(article: Article) {
      val intent = Intent(this,DescriptionActivity::class.java)
        intent.putExtra("urltoimage",article.urltoimage)
        intent.putExtra("description",article.description)
        intent.putExtra("title",article.title)
        intent.putExtra("source",article.source)
        intent.putExtra("date",article.date)
        startActivity(intent)
    }

}