package com.post.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.post.domain.models.Post

@Composable
fun PostItem(post: Post, onClick: () -> Unit = {}, onSwipe: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                onClick()
                       },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            fontWeight = FontWeight.SemiBold,
            text = post.description, maxLines = 2, overflow = TextOverflow.Ellipsis
        )
        Text(
            fontWeight = FontWeight.Light,
            text = "${post.author} - ${post.creationDate}"
        )
    }
}