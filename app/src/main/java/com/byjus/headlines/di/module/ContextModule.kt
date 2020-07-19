package com.byjus.headlines.di.module

import android.content.Context
import com.byjus.headlines.di.qualifiers.ApplicationContext
import com.byjus.headlines.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    @ApplicationContext
    @ApplicationScope
    fun provideContext():Context{
        return context
    }
}
