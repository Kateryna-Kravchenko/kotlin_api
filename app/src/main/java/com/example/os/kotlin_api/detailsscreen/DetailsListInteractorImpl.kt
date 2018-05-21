package com.example.os.kotlin_api.detailsscreen

import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable

class DetailsListInteractorImpl(var mDetailsListRepository: DetailsListRepository) : DetailsListInteractor {
    override fun executeUser(userId: Int): Observable<User>?  = mDetailsListRepository.getUser(userId)
    override fun executeComments(postId: Int): Observable<List<Comment>> = mDetailsListRepository.getNewComments(postId)

}