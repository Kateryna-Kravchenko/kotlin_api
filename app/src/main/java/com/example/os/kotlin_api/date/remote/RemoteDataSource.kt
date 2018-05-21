package com.example.os.kotlin_api.date.remote

import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable


interface RemoteDataSource {

    fun getPosts(): Observable<List<Post>>

    fun getUsers(): Observable<List<User>>

    fun getComments(postId: Int): Observable<List<Comment>>

}