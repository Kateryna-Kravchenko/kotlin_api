package com.example.os.kotlin_api.detailsscreen.ui

import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.User

interface DetailsView {
    fun showComments(comments: List<Comment>)
    fun showUserData(user: User)
}