package com.post.core.di

import com.post.core.util.NetWorkStatus
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDependencies = module {
    single<NetWorkStatus> { NetWorkStatus(androidContext()) }
}