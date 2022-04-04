package com.teck.reddittopchart.presenter.main

import androidx.paging.cachedIn
import com.teck.reddittopchart.domain.AppState
import com.teck.reddittopchart.domain.repository.Repository
import com.teck.reddittopchart.presenter.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : BaseViewModel() {
    override fun getData() {
        viewModelCoroutineScope.launch {
            liveData.postValue(
                AppState.Success(
                    repository.getData()
                )
            )
        }
    }

    suspend fun getMore() = repository.getMoreData().cachedIn(viewModelCoroutineScope)

    override fun handleError(throwable: Throwable) {
        liveData.postValue(AppState.Error(throwable))
    }
}