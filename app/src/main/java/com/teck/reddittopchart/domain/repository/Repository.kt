package com.teck.reddittopchart.domain.repository

import androidx.paging.PagingData
import com.teck.reddittopchart.domain.models.Children
import com.teck.reddittopchart.domain.models.Data
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getData(): Data
    suspend fun getMoreData(): Flow<PagingData<Children>>
}