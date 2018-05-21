package com.example.os.kotlin_api.listscreen.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.example.os.kotlin_api.PostApp
import com.example.os.kotlin_api.R
import com.example.os.kotlin_api.date.remote.http.json.Post
import com.example.os.kotlin_api.detailsscreen.ui.DetailsActivity
import com.example.os.kotlin_api.listscreen.MainListPresenter
import com.example.os.kotlin_api.listscreen.ui.adapters.OnItemClickListener
import com.example.os.kotlin_api.listscreen.ui.adapters.PostAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item_list.*


class ListActivity : AppCompatActivity(), ListView, OnItemClickListener {

    lateinit var mListPresenter: MainListPresenter
    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setupInjection()
        mListPresenter.getPosts()
        swipe_refresh_list.setOnRefreshListener({ refresh() })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, posts: List<Post>) {
        recyclerView.adapter = adapter
        adapter.setPost(posts)

    }

    private fun refresh() {
        mListPresenter.getPosts()
    }

    private fun setupInjection() {
        val app = application as PostApp
        val component = app.getMainListComponent(this, this)
        component.inject(this)
        mListPresenter = component.mainListPresenter()
        adapter = component.postAdapter()
    }

    override fun showPosts(posts: List<Post>) {
        if (swipe_refresh_list.isRefreshing) swipe_refresh_list.isRefreshing = false
        setupRecyclerView(item_list, posts)
    }

    override fun onItemClick(userId: Int, postId: Int) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.ARG_USER_ID, userId)
            putExtra(DetailsActivity.ARG_POST_ID, postId)
        }
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }


}
