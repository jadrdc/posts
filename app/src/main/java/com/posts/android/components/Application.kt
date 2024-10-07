package com.posts.android.components

import android.app.Application
import com.post.data.di.dataDependencyInjections
import com.post.domain.di.domainDependencyInjection
import com.post.presentation.di.viewModelModuleDepedency
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            androidLogger()
            modules(
                dataDependencyInjections,
                domainDependencyInjection,
                viewModelModuleDepedency
            )
        }
    }
}
