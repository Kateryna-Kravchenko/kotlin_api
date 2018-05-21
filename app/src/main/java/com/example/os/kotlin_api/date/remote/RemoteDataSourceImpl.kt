package com.example.os.kotlin_api.date.remote


import com.example.os.kotlin_api.date.remote.http.ApiClient
import com.example.os.kotlin_api.date.remote.http.ApiService
import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable


class RemoteDataSourceImpl(private val mHttpApi: ApiService) : RemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return ApiClient().createRetrofitInstance().getPosts()
    }

    override fun getUsers(): Observable<List<User>> {
        return mHttpApi.getUsers()
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return mHttpApi.getComments(postId)
    }

}