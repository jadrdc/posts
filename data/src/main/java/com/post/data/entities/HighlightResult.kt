package com.post.data.entities

data class HighlightResult(
    val author: Author,
    val comment_text: CommentText,
    val story_text: StoryText,
    val story_title: StoryTitle,
    val story_url: StoryUrl,
    val title: Title,
    val url: Url
)