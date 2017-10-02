package com.pulsetask.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pulsetask.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ayoola on 01/10/2017.
 */

internal object ServiceGenerator {

    private val dataRetrofit: Retrofit
        get() = getBaseRetrofit(BuildConfig.URL_PULSE)
                .build()

    val dataService: DataService
        get() = dataRetrofit
                .create(DataService::class.java)

    private fun getBaseRetrofit(baseUrl: String): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }
}

