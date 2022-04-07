package com.teck.reddittopchart.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.teck.reddittopchart.data.repository.datasource.api.BackendApi
import com.teck.reddittopchart.domain.models.Children
import com.teck.reddittopchart.domain.models.Data

class CloudSourceImpl(private val api: BackendApi) : CloudSource, PagingSource<String, Children>() {
    override suspend fun getData(): Data = api.getPosts()
    override suspend fun getMoreData(after: String): Data = api.getMorePosts(after)
    override fun getRefreshKey(state: PagingState<String, Children>): String? {
          return state.pages.first().nextKey
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Children> {
        val nextPage: String = params.key.toString() ?: ""
        val data = api.getMorePosts(nextPage).data.children
        val key = api.getMorePosts(nextPage).data.after
        return try {
            LoadResult.Page(
                data = data,
                prevKey = "",
                nextKey = key
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}