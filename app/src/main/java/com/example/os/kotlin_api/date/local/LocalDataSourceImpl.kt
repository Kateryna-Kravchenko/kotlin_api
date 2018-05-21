package com.example.os.kotlin_api.date.local

import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.date.remote.http.json.User
import io.reactivex.Observable

class LocalDataSourceImpl : LocalDataSource {

    private var postsCache: List<Post> = ArrayList()
    private var usersCache: List<User> = ArrayList()

    override fun savePosts(posts: List<Post>) {
        postsCache = posts
    }

    override fun saveUsers(users: List<User>) {
        usersCache = users
    }

    override fun getPostsLocal(): Observable<List<Post>> {
        return Observable.just(postsCache)
    }

    override fun getUserByIdLocal(userId: Int): Observable<Any> {
        if(usersCache.isEmpty()){
          return Observable.just(Observable.empty<Any>() )
        }
        else {
            val selectedUser: User = usersCache.single { user -> user.id == userId }
            when (selectedUser.id == 0) {
                true -> return Observable.just(selectedUser)
                false -> return Observable.just(selectedUser)
            }
        }
    }

}