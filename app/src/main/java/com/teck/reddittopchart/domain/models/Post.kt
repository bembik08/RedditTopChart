package com.teck.reddittopchart.domain.models

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("author_fullname")
    val authorFullName: String,
    val title: String,
    val thumbnail: String,
    val created: Int,
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Int
)