package com.example.poverty.Database

import androidx.lifecycle.LiveData

class PostRepository(private val postDatabaseDao: PostDatabaseDao) {

    val allPost: LiveData<List<RecycleViewPost>> = postDatabaseDao.getAllPost()

    suspend fun insert(post:RecycleViewPost){
        postDatabaseDao.insert(post)
    }

    suspend fun delete(){
        postDatabaseDao.deleteAll()
    }

}