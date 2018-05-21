package com.example.os.kotlin_api.listscreen

import com.example.os.kotlin_api.date.local.LocalDataSource
import com.example.os.kotlin_api.date.remote.RemoteDataSource
import com.example.os.kotlin_api.date.remote.http.json.Post
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class MainListRepositoryImpl(val mLocalDataSource: LocalDataSource,
                             val mRemoteDataSource: RemoteDataSource) : MainListRepository {

    override fun getNewPosts(): Observable<List<Post>> {
        val localList = mLocalDataSource.getPostsLocal()
                .subscribeOn(Schedulers.computation())
        val remoteList = mRemoteDataSource.getPosts()
                .map({ list ->
                    Observable.create<Observable<List<Post>>> { subscriber ->
                        mLocalDataSource.savePosts(list)
                        subscriber.onComplete()
                    }.subscribeOn(Schedulers.computation()).subscribe()
                    list
                })
                .subscribeOn(Schedulers.io())
        return Observable
                .concat(remoteList, localList)
    }
}