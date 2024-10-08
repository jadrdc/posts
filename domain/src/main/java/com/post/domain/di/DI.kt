package com.post.domain.di

import com.post.domain.paging.PostOfflinePagingSourceFactory
import com.post.domain.paging.PostPagingSourceFactory
import com.post.domain.paging.PostsOfflinePagingSource
import com.post.domain.paging.PostsPagingSource
import com.post.domain.usecase.DeletePostUseCase
import com.post.domain.usecase.GetPostOfflineUseCase
import com.post.domain.usecase.GetPostUseCase
import org.koin.dsl.module

val domainDependencyInjection = module {
    single { GetPostUseCase(get()) } // Posts DAO instance
    single { GetPostOfflineUseCase(get()) } // Posts DAO instance
    single { DeletePostUseCase(get()) }
    factory { PostPagingSourceFactory(get()) }
    factory { PostsPagingSource(get()) }
    factory { PostOfflinePagingSourceFactory(get()) }
    factory { PostsOfflinePagingSource(get()) }
}