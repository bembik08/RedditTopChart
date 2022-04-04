package com.teck.reddittopchart.presenter.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teck.reddittopchart.databinding.ItemPostBinding
import com.teck.reddittopchart.domain.models.Children
import com.teck.reddittopchart.domain.models.Post

class PostAdapter : PagingDataAdapter<Children, PostAdapter.PostViewHolder>(DataComparator){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.data?.let { holder.bind(it) }
    }

    object DataComparator : DiffUtil.ItemCallback<Children>() {
        override fun areItemsTheSame(oldItem: Children, newItem: Children) =
            oldItem.data == newItem.data

        override fun areContentsTheSame(oldItem: Children, newItem: Children) =
            oldItem == newItem
    }

    inner class PostViewHolder(
        private val viewBinding: ItemPostBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(post: Post) {
            viewBinding.contentPost.text = post.title
            viewBinding.countOfMessages.text = post.numComments.toString()
            viewBinding.countLikes.text = post.totalAwardsReceived.toString()
        }
    }
}