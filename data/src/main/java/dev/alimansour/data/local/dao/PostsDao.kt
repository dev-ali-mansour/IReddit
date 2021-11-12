package dev.alimansour.data.local.dao

import androidx.room.*
import dev.alimansour.data.local.entity.PostEntity
import io.reactivex.Observable

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity)

    @Delete
    fun delete(post: PostEntity)

    @Query("DELETE FROM posts")
    fun clearPosts()

    @Query("SELECT * FROM posts")
    fun getPosts(): Observable<List<PostEntity>>
}