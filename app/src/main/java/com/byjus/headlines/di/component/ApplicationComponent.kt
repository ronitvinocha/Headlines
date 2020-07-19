package com.byjus.headlines.di.component

import android.content.Context
import com.byjus.headlines.MyApplication
import com.byjus.headlines.di.module.ContextModule
import com.byjus.headlines.di.module.PiccassoModule
import com.byjus.headlines.di.module.RetrofitModule
import com.byjus.headlines.di.qualifiers.ApplicationContext
import com.byjus.headlines.di.retrofit.GetTopNewsInterFace
import com.byjus.headlines.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class,RetrofitModule::class])
interface ApplicationComponent {
    fun gettopnews():GetTopNewsInterFace
    @ApplicationContext
    fun getContext():Context

    fun injectApplication(myApplication: MyApplication)
}