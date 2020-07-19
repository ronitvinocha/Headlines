package com.byjus.headlines.di.module

import com.byjus.headlines.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class okhttpclientmodule {

    @Provides
    fun getokhttpclient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}