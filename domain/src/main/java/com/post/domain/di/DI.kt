package com.post.domain.di

import com.post.domain.usecase.GetPostUseCase
import org.koin.dsl.module

val domainDependencyInjection = module {
    single { GetPostUseCase(get()) } // Posts DAO instance
}