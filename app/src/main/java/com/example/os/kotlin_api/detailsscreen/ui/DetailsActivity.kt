package com.example.os.kotlin_api.detailsscreen.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.transition.Explode
import android.view.Window
import com.example.os.kotlin_api.PostApp
import com.example.os.kotlin_api.R
import com.example.os.kotlin_api.date.remote.http.json.Comment
import com.example.os.kotlin_api.date.remote.http.json.User
import com.example.os.kotlin_api.detailsscreen.DetailsListPresenter
import com.example.os.kotlin_api.detailsscreen.ui.adapters.CommentAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_detail.*
import kotlinx.android.synthetic.main.user_info.*

class DetailsActivity : AppCompatActivity(), DetailsView {


    lateinit var presenter: DetailsListPresenter
    lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        val userId = intent.extras.getInt(ARG_USER_ID, -1)
        val postId = intent.extras.getInt(ARG_POST_ID, -1)
        setupInjection()
        presenter.getComments(postId)
        presenter.getUser(userId)
        swipe_refresh_detail.setOnRefreshListener({ refresh(postId) })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, posts: List<Comment>) {
        recyclerView.adapter = adapter
        adapter.setComments(posts)

    }

    private fun refresh(postId: Int) {
        if (swipe_refresh_detail.isRefreshing) swipe_refresh_detail.isRefreshing = false
        presenter.getComments(postId)
    }

    private fun setupInjection() {
        val app = application as PostApp
        val component = app.getDetailsListComponent(this)
        component.inject(this)
        presenter = component.detailsListPresenter()
        adapter = component.commentAdapter()
    }
    override fun showComments(comments: List<Comment>) {
        //   if (swipe_refresh_list.isRefreshing) swipe_refresh_list.isRefreshing = false
        setupRecyclerView(item_detail, comments)
    }


    override fun showUserData(user: User) {
        toolbar_title.text = String.format(getString(R.string.user_detail_title),
                user.username, user.address.city)
        name.text = user.name
        phone.text = user.phone
        web_site.text = user.website
        company_name.text = user.company.name

    }

    companion object {
        const val ARG_USER_ID = "userId"
        const val ARG_POST_ID = "postId"
    }


    }

