package com.post.data.entities

data class Hit(
    val _highlightResult: HighlightResult,
    val _tags: List<String>,
    val author: String,
    val children: List<Int>,
    val comment_text: String,
    val created_at: String,
    val created_at_i: Int,
    val num_comments: Int,
    val objectID: String,
    val parent_id: Int,
    val points: Int,
    val story_id: Int,
    val story_text: String,
    val story_title: String,
    val story_url: String,
    val title: String,
    val updated_at: String,
    val url: String
)