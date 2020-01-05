package com.example.poverty.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(post: RecycleViewPost)

    @Update
    fun update(post: RecycleViewPost)

    @Query("Delete from recycle_post_table")
    suspend fun deleteAll()


    // get() function that takes a Long key argument and returns a nullable SleepNight
    //key. You use the colon notation in the query to reference arguments in the function.
    @Query ("SELECT * from recycle_post_table WHERE postID = :key")
    fun get(key:Long): RecycleViewPost?

    @Query("SELECT * FROM recycle_post_table ORDER BY postID DESC LIMIT 1")
    fun getPost(): RecycleViewPost?

    @Query ("SELECT * from recycle_post_table")
    fun getAllPost():LiveData<List<RecycleViewPost>>

}