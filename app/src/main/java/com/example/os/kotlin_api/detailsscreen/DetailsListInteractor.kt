package com.example.os.kotlin_api.detailsscreen

import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable

interface DetailsListInteractor {

    fun executeUser(userId:Int):Observable<User>?
    fun executeComments(postId:Int):  Observable<List<Comment>>

}