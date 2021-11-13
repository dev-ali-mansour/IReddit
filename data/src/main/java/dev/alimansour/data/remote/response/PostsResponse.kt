package dev.alimansour.data.remote.response

import com.google.gson.annotations.SerializedName

data class PostsResponse(
    @field:SerializedName("data")
    val data: Data,
)

data class Data(
    @field:SerializedName("after")
    val after: String?,
    @field:SerializedName("children")
    val children: List<Child>,
)

data class Child(
    @field:SerializedName("data")
    val postData: PostData
)

data class PostData(
    @field:SerializedName("author")
    val author: String?,
    @field:SerializedName("is_video")
    val isVideo: Boolean,
    @field:SerializedName("preview")
    val preview: Preview?,
    @field:SerializedName("title")
    val title: String,
//    @field:SerializedName("url_overridden_by_dest")
    @field:SerializedName("permalink")
    val url: String?
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