package com.example.os.kotlin_api

import com.example.os.kotlin_api.date.local.LocalDataSource
import com.example.os.kotlin_api.date.local.LocalDataSourceImpl
import com.example.os.kotlin_api.date.remote.http.json.Post
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
class LocalDataSourceTest {
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        localDataSource = LocalDataSourceImpl()
    }

    @Test
    fun savePosts_retrievePosts() {
        val postsToDb: List<Post> = listOf(
                Post(1, "Title1", "Body1", 1),
                Post(3, "Title3", "Body3", 3))
        localDataSource.savePosts(postsToDb)
        val postsFromDb = localDataSource.getPostsLocal().blockingFirst()
        assertSame(postsToDb, postsFromDb)
    }

}