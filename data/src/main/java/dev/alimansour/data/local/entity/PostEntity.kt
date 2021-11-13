package dev.alimansour.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val author: String?,
    val image: String?,
    val isVideo: Boolean,
    val url: String?
)