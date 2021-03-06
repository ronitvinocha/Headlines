package com.byjus.headlines.di.module

import android.view.View
import com.byjus.headlines.MainActivity
import com.byjus.headlines.RecyclerViewAdapter
import com.byjus.headlines.di.pojo.Articles
import com.byjus.headlines.di.scopes.ActivityScope
import com.byjus.headlines.di.scopes.ApplicationScope
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module(includes = [ActivityContextModule::class])
class AdapterModule {
    @Provides
    @ActivityScope
    fun getNewsList(clickListener: RecyclerViewAdapter.ClickListener):RecyclerViewAdapter{
        return RecyclerViewAdapter(clickListener)
    }

    @Provides
    @ActivityScope
    fun getClickListner(mainActivity: MainActivity):RecyclerViewAdapter.ClickListener{
        return mainActivity
    }


}