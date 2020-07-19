package com.byjus.headlines.di.module

import com.byjus.headlines.di.retrofit.GetTopNewsInterFace
import com.byjus.headlines.di.scopes.ApplicationScope
import com.google.gson.internal.bind.TypeAdapters.CLASS
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [okhttpclientmodule::class])
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun getTopnewsInterFaceL(retrofit: Retrofit): GetTopNewsInterFace {
        return retrofit.create(GetTopNewsInterFace::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetroFit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


}