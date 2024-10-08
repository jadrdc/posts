package com.post.data.di

import android.app.Application
import androidx.room.Room
import com.post.data.impl.OfflineDataSourceImp
import com.post.data.impl.OnlineDataSourceImp
import com.post.data.impl.PostRepositoryImpl
import com.post.data.local.AppDatabase
import com.post.domain.paging.PostsPagingSource
import com.post.data.services.PostService
import com.post.domain.interfaces.PostLocalDataSource
import com.post.domain.interfaces.PostRemoteDataSource
import com.post.domain.interfaces.PostRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val URL = "https://hn.algolia.com/api/v1/"
private const val DATABASE = "database"
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
    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, DATABASE
        ).fallbackToDestructiveMigration().build()
    }
    single { get<AppDatabase>().postsDAO } // Posts DAO instance
    single<PostRemoteDataSource> { OnlineDataSourceImp(get(), get()) }
    single<PostLocalDataSource> { OfflineDataSourceImp(get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
}