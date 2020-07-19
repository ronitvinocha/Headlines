package com.byjus.headlines.di.retrofit

import com.byjus.headlines.di.pojo.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import java.util.*

interface GetTopNewsInterFace {
 @GET("top-headlines")
  fun getnews(@Query("country") country:String,@Query("category") category:String,@Query("apiKey") apikey:String): Call <News>
}