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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val deletePostUseCase: DeletePostUseCase,
    private val netWorkStatus: NetWorkStatus,
    private val paging: PostPagingSourceFactory,
    private val offlinePostPaging: PostOfflinePagingSourceFactory
) : ViewModel(
) {
    private val _networkStatusStateFlow = MutableStateFlow(false)

    val posts: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10
        ),
        pagingSourceFactory = {
            paging.getPagingSource()
        }
    ).flow

    val offlinePost: Flow<PagingData<Post>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10
        ),
        pagingSourceFactory = {
            offlinePostPaging.getPagingSource()
        }
    ).flow


    init {
        viewModelScope.launch {
            netWorkStatus.getNetworkStatus().collect { status ->
                if (!status && _networkStatusStateFlow.value) {

                }
                if (status && !_networkStatusStateFlow.value) {

                }

                if (status != _networkStatusStateFlow.value) {
                    _networkStatusStateFlow.value = status
                }
            }
        }
    }

    fun onDelete(post: Post) {
        viewModelScope.launch {
            deletePostUseCase(post)
        }
    }
}