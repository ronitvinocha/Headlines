package com.byjus.headlines.di.module

import android.content.Context
import com.byjus.headlines.di.qualifiers.ActivityContext
import com.byjus.headlines.di.qualifiers.ApplicationContext
import com.byjus.headlines.di.scopes.ActivityScope
import com.byjus.headlines.di.scopes.ApplicationScope
import com.squareup.picasso.Cache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.io.File

@Module(includes = [okhttpclientmodule::class])
class PiccassoModule {
    @Provides
    @ApplicationScope
    fun picasso(@ApplicationContext context: Context,okHttp3Downloader: OkHttp3Downloader):Picasso
    {
        return Picasso.Builder(context)
            .downloader(okHttp3Downloader)
            .build()
    }
    @Provides
    @ApplicationScope
    fun okhttpdownloader(okHttpClient: OkHttpClient):OkHttp3Downloader
    {
        return OkHttp3Downloader(okHttpClient)
    }

}