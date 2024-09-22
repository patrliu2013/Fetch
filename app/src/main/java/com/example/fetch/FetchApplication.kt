package com.example.fetch

import android.app.Application
import com.example.fetch.di.viewModelModule
import com.example.modules.apiMapperModule
import com.example.modules.networkModule
import com.example.modules.repositoryModule
import com.example.modules.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FetchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            // Reference Android context
            androidContext(this@FetchApplication)
            modules(
                viewModelModule,
                apiMapperModule,
                networkModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}