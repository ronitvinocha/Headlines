package com.byjus.headlines

import android.app.Activity
import android.app.Application
import com.byjus.headlines.di.component.ApplicationComponent
import com.byjus.headlines.di.component.DaggerApplicationComponent
import com.byjus.headlines.di.database.LocalDatabase
import com.byjus.headlines.di.module.ContextModule
import com.byjus.headlines.di.module.PiccassoModule
import javax.inject.Inject

class MyApplication():Application() {

    lateinit var  applicationComponent: ApplicationComponent
    @Inject lateinit var localDatabase: LocalDatabase
    override fun onCreate() {
        super.onCreate()
        applicationComponent=DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this)).build()
        applicationComponent.injectApplication(this)
        println(localDatabase)

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