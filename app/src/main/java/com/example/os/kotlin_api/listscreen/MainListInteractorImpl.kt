package com.example.os.kotlin_api.listscreen

import com.example.os.kotlin_api.date.remote.http.json.Post
import io.reactivex.Observable

class MainListInteractorImpl(var mListMainRepository: MainListRepository) : MainListInteractor {

    override fun execute(): Observable<List<Post>> {
        return mListMainRepository.getNewPosts()
    }

}