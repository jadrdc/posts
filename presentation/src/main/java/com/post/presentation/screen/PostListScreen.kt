package com.post.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.post.domain.models.Post
import com.post.presentation.ui.PostItem
import com.post.presentation.ui.SwipeToDeleteContainer
import com.post.presentation.viewmodel.PostViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostListScreen(viewModel: PostViewModel = koinViewModel(), onNavigate: (Post) -> Unit) {
    val state = viewModel.uiState.collectAsState().value
    LazyColumn(
        modifier = Modifier.padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(count = state.posts.size, key = { it }) { index ->
            val post = state.posts[index]
            SwipeToDeleteContainer(
                item = post,
                onDelete = {
                    viewModel.onDelete(post)
                }
            ) {
                Column {
                    PostItem(post = post, onClick = {
                        onNavigate(post)
                    })

                    if (index < state.posts.size - 1) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }
}