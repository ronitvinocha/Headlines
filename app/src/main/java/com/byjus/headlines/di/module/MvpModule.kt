package com.byjus.headlines.di.module

import com.byjus.headlines.MainContract
import com.byjus.headlines.PresenterImpl
import com.byjus.headlines.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MvpModule (var mview:MainContract.ViewCallBack)
{
    @Provides
    @ActivityScope
    fun providesPresenter():MainContract.ViewCallBack
    {
        return (mview)
    }
}