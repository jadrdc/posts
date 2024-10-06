package com.post.data.di

import androidx.room.Room
import com.post.data.local.AppDatabase
import com.post.data.services.PostService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val URL = "https://hn.algolia.com/api/v1/"
private const val DATABASE = "DATABASE"
val dataDependencyInjections = module {
    single {
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }.build()
    }
    single {
        Retrofit.Builder().client(get()).baseUrl(
            URL
        )
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    single<PostService> { get<Retrofit>().create(PostService::class.java) }
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, DATABASE
        ).build()
    }
    single { get<AppDatabase>().postsDAO() } // Posts DAO instance

}