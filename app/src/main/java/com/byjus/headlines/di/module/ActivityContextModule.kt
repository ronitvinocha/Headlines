package com.byjus.headlines.di.module
import android.content.Context
import com.byjus.headlines.MainActivity
import com.byjus.headlines.di.qualifiers.ActivityContext
import com.byjus.headlines.di.scopes.ActivityScope
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