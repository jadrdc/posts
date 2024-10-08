package com.post.presentation.di

import com.post.presentation.viewmodel.PostViewModel
import org.koin.dsl.module

val viewModelModuleDepedency = module {
    single { PostViewModel(get(), get(), get()) }
}

