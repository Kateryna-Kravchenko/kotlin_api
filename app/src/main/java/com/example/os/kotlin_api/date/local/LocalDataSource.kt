package com.example.os.kotlin_api.date.local

import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable

interface LocalDataSource {

    fun savePosts(posts: List<Post>)

    fun saveUsers(users: List<User>)

    fun getPostsLocal(): Observable<List<Post>>

    fun getUserByIdLocal(userId: Int): Observable<Any>

}