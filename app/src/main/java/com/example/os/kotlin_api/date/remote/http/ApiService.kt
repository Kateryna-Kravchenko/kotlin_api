package com.example.os.kotlin_api.date.remote.http

import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Observable<List<Comment>>

}