package com.byjus.headlines.di.component

import android.content.Context
import com.byjus.headlines.MyApplication
import com.byjus.headlines.di.database.LocalDatabase
import com.byjus.headlines.di.module.ContextModule
import com.byjus.headlines.di.module.PiccassoModule
import com.byjus.headlines.di.module.RetrofitModule
import com.byjus.headlines.di.module.RoomModule
import com.byjus.headlines.di.qualifiers.ApplicationContext
import com.byjus.headlines.di.retrofit.GetTopNewsInterFace
import com.byjus.headlines.di.scopes.ApplicationScope
import com.squareup.picasso.Picasso
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class,RetrofitModule::class,RoomModule::class,PiccassoModule::class])
interface ApplicationComponent {
    fun gettopnews():GetTopNewsInterFace
    @ApplicationContext
    fun getContext():Context
    fun getDatabase():LocalDatabase
    fun getPiccaso():Picasso
    fun injectApplication(myApplication: MyApplication)
}