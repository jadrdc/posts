package com.post.domain.di

import com.post.domain.usecase.DeletePostUseCase
import com.post.domain.usecase.GetPostOfflineUseCase
import com.post.domain.usecase.GetPostUseCase
import org.koin.dsl.module

val domainDependencyInjection = module {
    single { GetPostUseCase(get()) } // Posts DAO instance
    single { GetPostOfflineUseCase(get()) } // Posts DAO instance
    single { DeletePostUseCase(get()) } // Posts DAO instance
}