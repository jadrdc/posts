package com.posts.android.components

import android.app.Application
import com.post.data.di.dataDependencyInjections
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                dataDependencyInjections
            )
        }
    }
}
