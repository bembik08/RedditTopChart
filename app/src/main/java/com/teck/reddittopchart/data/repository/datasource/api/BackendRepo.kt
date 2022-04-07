package com.teck.reddittopchart.data.repository.datasource.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendRepo {
    val gson = Gson()
        .newBuilder()
        .setLenient()
        .create()
    val api: BackendApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(ApiUtils.getOkHttpClientBuilder())
            .build()
        adapter.create(BackendApi::class.java)
    }
}