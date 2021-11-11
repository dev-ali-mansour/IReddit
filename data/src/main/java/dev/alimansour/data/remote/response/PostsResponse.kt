package dev.alimansour.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @field:SerializedName("data")
    val data: Data,
)

data class Data(
    @field:SerializedName("children")
    val posts: List<Post>,
)

data class Post(
    @field:SerializedName("data")
    val postData: PostData
)

data class PostData(
    @field:SerializedName("author_fullname")
    val authorFullName: String,

    @field:SerializedName("is_video")
    val isVideo: Boolean,

    @field:SerializedName("preview")
    val preview: Preview,

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