package com.post.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.post.domain.models.Post
import com.post.domain.usecase.GetPostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val getPostUseCase: GetPostUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(PostState(posts = listOf()))
    val uiState: StateFlow<PostState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = getPostUseCase()) {
                is com.post.core.util.OperationResult.Error -> {}
                is com.post.core.util.OperationResult.Success -> {
                    _uiState.value = uiState.value.copy(posts = result.data)
                }
            }
        }
    }
}

data class PostState(var posts: List<Post>)