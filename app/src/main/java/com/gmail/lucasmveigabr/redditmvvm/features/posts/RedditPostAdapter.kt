package com.gmail.lucasmveigabr.redditmvvm.features.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.lucasmveigabr.redditmvvm.R
import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditChildrenData
import kotlinx.android.synthetic.main.reddit_post_holder.view.*
import java.util.*

class RedditPostAdapter(
    private val context: Context,
    private val onClickListener: (RedditChildrenData) -> Unit
) :
    RecyclerView.Adapter<RedditPostAdapter.RedditPostHolder>() {

    private var posts: List<RedditChildrenData> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.reddit_post_holder, parent, false)
        return RedditPostHolder(v, onClickListener)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: RedditPostHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun setPosts(posts: List<RedditChildrenData>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class RedditPostHolder(
        private val view: View,
        private val listener: (RedditChildrenData) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener { listener(posts[adapterPosition]) }
        }

        fun bind(item: RedditChildrenData) {
            Glide.with(view)
                .load(item.thumbnail)
                .into(view.thumbnailImageView)
            view.titleTextView.text = item.title
            view.authorTextView.text = item.author
        }

    }


}