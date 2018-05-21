package com.example.os.kotlin_api.listscreen.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.os.kotlin_api.R
import com.example.os.kotlin_api.date.remote.http.json.Post
import kotlinx.android.synthetic.main.item_list_content.view.*


class PostAdapter(var values: List<Post>, val onItemClickListener: OnItemClickListener) :
        RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    fun setPost(posts: List<Post>) {
        values = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.title.tag = item.id
        holder.contentViewUserId.tag = item.userId
        holder.contentViewUserId.text = item.body
        with(holder.itemView) {
            tag = item.id
            setOnItemClickListener(holder.itemView, holder.contentViewUserId.tag as Int, holder.title.tag as Int, onItemClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
        val contentViewUserId: TextView = view.content

    }

    fun setOnItemClickListener(view: View, userId: Int, postId: Int, onItemClickListener: OnItemClickListener) {
        view.setOnClickListener({
            onItemClickListener.onItemClick(userId, postId)

        })
    }
}