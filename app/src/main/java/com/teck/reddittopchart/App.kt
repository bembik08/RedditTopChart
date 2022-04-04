package com.teck.reddittopchart

import android.app.Application
import com.teck.reddittopchart.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    Di.sourceModules(),
                    Di.repositoryModule(),
                    Di.viewModules()
                )
            )
        }
    }
}