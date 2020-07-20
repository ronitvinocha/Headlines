package com.byjus.headlines.di.module

import android.content.Context
import com.byjus.headlines.di.qualifiers.ApplicationContext
import com.byjus.headlines.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

@Module
class okhttpclientmodule {

    @Provides
    @ApplicationScope
    fun okhttpclient(cache: okhttp3.Cache):OkHttpClient
    {
        return OkHttpClient.Builder().cache(cache).build()
    }
    @Provides
    @ApplicationScope
    fun cache(cachefile: File): okhttp3.Cache
    {
         return okhttp3.Cache(cachefile, 10 * 1000 * 1000) //10 MB
    }

    @Provides
    @ApplicationScope
    fun file( @ApplicationContext context:Context):File
    {
        var file=File(context.cacheDir,"headlines")
        file.mkdirs()
        return file
    }
}