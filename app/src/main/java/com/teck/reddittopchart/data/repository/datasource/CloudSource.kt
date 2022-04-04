package com.teck.reddittopchart.data.repository.datasource

import com.teck.reddittopchart.domain.models.Data

interface CloudSource {
   suspend fun getData(): Data
   suspend fun getMoreData(after: String): Data
}