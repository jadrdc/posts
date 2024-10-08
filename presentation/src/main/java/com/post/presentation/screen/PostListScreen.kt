package com.post.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.post.domain.models.Post
import com.post.presentation.ui.CustomDialog
import com.post.presentation.ui.LoadingBar
import com.post.presentation.ui.PostItemSwipeable
import com.post.presentation.viewmodel.PostViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostListScreen(viewModel: PostViewModel = koinViewModel(), onNavigate: (Post) -> Unit) {
    val posts = viewModel.pagedPosts.collectAsLazyPagingItems()
    var shouldShowDialog by remember { mutableStateOf(false) } // 1

    if (shouldShowDialog) {
        CustomDialog {
            shouldShowDialog = false
        }
    }
    when (posts.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingBar()
        }

        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 32.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (posts.itemCount > 0) {
                    items(
                        count = posts.itemCount
                    ) { index ->
                        posts[index]?.let { item ->
                            PostItemSwipeable(post = item,
                                onDelete = {
                                    viewModel.onDelete(item)
                                    posts.refresh()
                                }, onNavigate = {
                                    onNavigate(item)
                                }, isLastElement = index < posts.itemCount - 1
                            )
                        }
                    }
                }

                item {
                    when (posts.loadState.append) {
                        is LoadState.NotLoading -> {}
                        is LoadState.Loading -> {
                            LoadingBar()
                        }

                        is LoadState.Error -> {
                            shouldShowDialog = true
                        }
                    }
                }
            }
        }

        is LoadState.Error -> {
            shouldShowDialog = true
        }

    }
}


