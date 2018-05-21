package com.example.os.kotlin_api.detailsscreen

import com.example.os.kotlin_api.date.local.LocalDataSource
import com.example.os.kotlin_api.date.remote.RemoteDataSource
import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DetailsListRepositoryImpl(val mLocalDataSource: LocalDataSource,
                                val mRemoteDataSource: RemoteDataSource) : DetailsListRepository {

    override fun getNewComments(postId: Int): Observable<List<Comment>> = mRemoteDataSource.getComments(postId)

    override fun getUser(userId: Int): Observable<User>? = mLocalDataSource.getUserByIdLocal(userId)
            .flatMap { user ->
                if (user is User) {
                    return@flatMap Observable.just(user)
                } else {
                    return@flatMap mRemoteDataSource.getUsers()
                            .map({ list ->
                                Observable.create<Observable<List<User>>> { subscriber ->
                                    mLocalDataSource.saveUsers(list)
                                    subscriber.onComplete()
                                }.subscribeOn(Schedulers.computation()).subscribe()
                                list.single { user -> user.id == userId }
                            })
                            .subscribeOn(Schedulers.io())
                }
            }
            .subscribeOn(Schedulers.io())

}

