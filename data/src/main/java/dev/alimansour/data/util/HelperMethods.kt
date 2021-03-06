package dev.alimansour.data.util

import dev.alimansour.data.local.entity.PostEntity
import dev.alimansour.data.remote.response.PostsResponse
import dev.alimansour.domain.model.Post

fun Post.toEntity(): PostEntity = PostEntity(id, title, author, image, isVideo, url)

fun PostEntity.toModel(): Post = Post(id, title, author, image, isVideo, url, "foo")

fun PostsResponse.toModel(): List<Post> = data.children.map { post ->
    val image =
        when {
            post.postData.preview == null -> null
            post.postData.preview.images.isNullOrEmpty() -> null
            else -> post.postData.preview.images[0].source.url
        }
    Post(
        0,
        post.postData.title,
        post.postData.author,
        image?.replace("&amp;", "&"),
        post.postData.isVideo,
        "https://www.reddit.com/${post.postData.url}",
        this.data.after
    )
}