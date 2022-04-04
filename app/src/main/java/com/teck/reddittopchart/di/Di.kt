package com.teck.reddittopchart.di

import com.teck.reddittopchart.data.repository.RepositoryImpl
import com.teck.reddittopchart.data.repository.datasource.CloudSource
import com.teck.reddittopchart.data.repository.datasource.CloudSourceImpl
import com.teck.reddittopchart.data.repository.datasource.api.BackendRepo
import com.teck.reddittopchart.domain.repository.Repository
import com.teck.reddittopchart.presenter.main.MainFragment
import com.teck.reddittopchart.presenter.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {
    fun sourceModules() = module {
        single<CloudSource> {
            CloudSourceImpl(BackendRepo.api)
        }
    }

    fun repositoryModule() = module {
        single<Repository> {
            RepositoryImpl(get(), BackendRepo.api)
        }
    }

    fun viewModules() = module {
        scope<MainFragment> {
            viewModel { MainViewModel(get()) }
        }
    }
}