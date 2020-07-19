package com.byjus.headlines

import android.app.Activity
import android.app.Application
import com.byjus.headlines.di.component.ApplicationComponent
import com.byjus.headlines.di.component.DaggerApplicationComponent
import com.byjus.headlines.di.module.ContextModule
import com.byjus.headlines.di.module.PiccassoModule

class MyApplication():Application() {

    lateinit var  applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent=DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this)).build()
        applicationComponent.injectApplication(this)


    }

    companion object{
        fun get(activity: Activity):MyApplication{
        return activity.application as MyApplication
     }
    }
    fun getapplicationComponent():ApplicationComponent{
        return applicationComponent;
    }
}