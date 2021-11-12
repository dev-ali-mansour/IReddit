package dev.alimansour.domain.model

data class Post(
    val title: String,
    val author: String,
    val image: String?,
    val isVideo: Boolean
)
