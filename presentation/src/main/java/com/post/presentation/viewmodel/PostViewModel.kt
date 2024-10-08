package com.post.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.post.core.util.NetWorkStatus
import com.post.domain.models.Post
import com.post.domain.paging.PostPagingSourceFactory
import com.post.domain.usecase.DeletePostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val deletePostUseCase: DeletePostUseCase,
    private val netWorkStatus: NetWorkStatus,
    private val paging: PostPagingSourceFactory
) : ViewModel(
) {
    private val _networkStatusStateFlow = MutableStateFlow(false)

    val posts = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 10
        ),
        pagingSourceFactory = {
            paging.getPagingSource()
        }
    ).flow


    init {
        viewModelScope.launch {
            netWorkStatus.getNetworkStatus().collect { status ->
                if (status != _networkStatusStateFlow.value) {
                    _networkStatusStateFlow.value = status
                }
                if (!status) {

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