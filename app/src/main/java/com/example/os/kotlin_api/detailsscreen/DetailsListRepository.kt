package com.example.os.kotlin_api.detailsscreen

import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable

interface DetailsListRepository {
    fun getNewComments(postId: Int): Observable<List<Comment>>
    fun getUser(userId: Int):Observable<User>?

}