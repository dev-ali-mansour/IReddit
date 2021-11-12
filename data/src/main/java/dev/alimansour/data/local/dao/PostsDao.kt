package dev.alimansour.data.local.dao

import androidx.room.*
import dev.alimansour.data.local.entity.PostEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity): Completable

    @Delete
    fun delete(post: PostEntity): Completable

    @Query("DELETE FROM posts")
    fun clearPosts(): Completable

    @Query("SELECT * FROM posts")
    fun getPosts(): Observable<List<PostEntity>>
}