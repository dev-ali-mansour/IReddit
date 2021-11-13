package dev.alimansour.domain.model

data class Post(
    val id: Int,
    val title: String,
    val author: String?,
    val image: String?,
    val isVideo: Boolean,
    val url: String?,
    val after: String?
)
