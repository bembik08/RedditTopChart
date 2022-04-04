package com.teck.reddittopchart.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.teck.reddittopchart.data.repository.datasource.CloudSource
import com.teck.reddittopchart.data.repository.datasource.CloudSourceImpl
import com.teck.reddittopchart.data.repository.datasource.api.BackendApi
import com.teck.reddittopchart.domain.models.Children
import com.teck.reddittopchart.domain.models.Data
import com.teck.reddittopchart.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dataSource: CloudSource,
    private val api: BackendApi
) : Repository {
    override suspend fun getData(): Data = dataSource.getData()
    override suspend fun getMoreData(): Flow<PagingData<Children>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { CloudSourceImpl(api) }
    ).flow
}