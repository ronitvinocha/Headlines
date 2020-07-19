package com.byjus.headlines.di.component

import android.content.Context
import com.byjus.headlines.MainActivity
import com.byjus.headlines.di.module.ActivityContextModule
import com.byjus.headlines.di.module.AdapterModule
import com.byjus.headlines.di.module.MvpModule
import com.byjus.headlines.di.qualifiers.ActivityContext
import com.byjus.headlines.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [MvpModule::class,AdapterModule::class],dependencies = [ApplicationComponent::class])
interface MainActivityComponent
{
    @ActivityContext
    fun getContext():Context
    fun inject(target: MainActivity)

}