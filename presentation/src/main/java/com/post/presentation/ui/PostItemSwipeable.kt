package com.post.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.post.domain.models.Post

@Composable
fun PostItemSwipeable(
    post: Post,
    onDelete: () -> Unit,
    onNavigate: () -> Unit,
    isLastElement: Boolean
) {
    SwipeToDeleteContainer(
        item = post,
        onDelete = {
            onDelete()
        }
    ) {
        Column {
            PostItem(post = post, onClick = {
                onNavigate()
            })

            if (isLastElement) {
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}