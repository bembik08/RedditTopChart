package com.teck.reddittopchart.data.repository.datasource.api

import okhttp3.OkHttpClient

object ApiUtils {
    fun getOkHttpClientBuilder(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
        return httpClient.build()
    }
}