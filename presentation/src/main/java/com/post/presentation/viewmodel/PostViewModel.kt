package com.post.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.post.core.util.NetWorkStatus
import com.post.domain.models.Post
import com.post.domain.paging.PostOfflinePagingSourceFactory
import com.post.domain.paging.PostPagingSourceFactory
import com.post.domain.usecase.DeletePostUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class PostViewModel(
    private val deletePostUseCase: DeletePostUseCase,
    private val netWorkStatus: NetWorkStatus,
    private val paging: PostPagingSourceFactory,
    private val offlinePostPaging: PostOfflinePagingSourceFactory
) : ViewModel(
) {
    private val _networkStatusStateFlow = MutableStateFlow(true)
    val isOnline: StateFlow<Boolean>
        get() = _networkStatusStateFlow.asStateFlow()


    private val posts: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10
        ),
        pagingSourceFactory = {
            paging.getPagingSource()
        }
    ).flow

    private val offlinePost: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10
        ),
        pagingSourceFactory = {
            offlinePostPaging.getPagingSource()
        }
    ).flow

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagedPosts: Flow<PagingData<Post>> = _networkStatusStateFlow.flatMapLatest { isOnline ->
        if (isOnline) {
            posts // Use online data source
        } else {
            offlinePost // Use offline data source
        }
    }

    init {
        viewModelScope.launch {
            netWorkStatus.getNetworkStatus().collect { status ->
                _networkStatusStateFlow.value = status
            }
        }
    }

    fun onDelete(post: Post) {
        viewModelScope.launch {
            deletePostUseCase(post)
        }
    }
}