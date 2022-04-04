package com.teck.reddittopchart.data.repository.datasource.api

import com.teck.reddittopchart.domain.models.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendApi {
    @GET("top.json")
    suspend fun getPosts(): Data
    @GET("top.json")
    suspend fun getMorePosts(@Query("after") after: String): Data
}