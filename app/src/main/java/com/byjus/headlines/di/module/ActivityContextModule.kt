package com.byjus.headlines.di.module

import android.app.Activity
import android.content.Context
import com.byjus.headlines.MainActivity
import com.byjus.headlines.di.qualifiers.ActivityContext
import com.byjus.headlines.di.scopes.ActivityScope
import com.byjus.headlines.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ActivityContextModule(val mainActivity: MainActivity) {

    @Provides
    @ActivityContext
    @ActivityScope
    fun provideContext():Context{
        return mainActivity

    }

    @Provides
    fun providesMainActivity():MainActivity
    {
        return mainActivity
    }
}