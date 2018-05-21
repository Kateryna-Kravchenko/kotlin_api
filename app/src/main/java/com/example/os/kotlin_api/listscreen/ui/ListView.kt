package com.example.os.kotlin_api.listscreen.ui

import com.example.os.kotlin_api.date.remote.http.json.Post


interface ListView {
    fun showPosts(posts: List<Post>)
}
