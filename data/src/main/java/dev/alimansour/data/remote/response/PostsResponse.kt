package dev.alimansour.data.remote.response

import com.google.gson.annotations.SerializedName
import dev.alimansour.domain.model.Post

data class PostsResponse(
    @field:SerializedName("data")
    val data: Data,
) {
    fun toModel(): List<Post> {
        return data.children.map { post ->
            val image =
                when {
                    post.postData.preview == null -> null
                    post.postData.preview.images.isNullOrEmpty() -> null
                    else -> post.postData.preview.images[0].source.url
                }
            Post(
                post.postData.title,
                post.postData.authorFullName,
                image,
                post.postData.isVideo
            )
        }
    }
}

data class Data(
    @field:SerializedName("children")
    val children: List<Child>,
)

data class Child(
    @field:SerializedName("data")
    val postData: PostData
)

data class PostData(
    @field:SerializedName("author_fullname")
    val authorFullName: String,

    @field:SerializedName("is_video")
    val isVideo: Boolean,

    @field:SerializedName("preview")
    val preview: Preview?,

    @field:SerializedName("title")
    val title: String,
)

data class Preview(
    @field:SerializedName("images")
    val images: List<Image>
)

data class Image(
    @field:SerializedName("source")
    val source: Source
)

data class Source(
    @field:SerializedName("url")
    val url: String
)