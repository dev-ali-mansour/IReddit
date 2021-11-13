package dev.alimansour.ireddit.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.alimansour.domain.model.Post
import dev.alimansour.ireddit.R
import dev.alimansour.ireddit.databinding.PostItemBinding
import javax.inject.Inject

class FavoritesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavoritesAdapter.NewsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = PostItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        private val binding: PostItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            val postedBy = "Posted By ${post.author}"
            binding.TitleTextView.text = post.title
            binding.authorTextView.text = postedBy
            binding.playImageView.isVisible = post.isVideo
            binding.favoriteImageButton.visibility = View.GONE

            post.image?.let {
                Glide.with(binding.postImageView.context).load(it)
                    .placeholder(R.drawable.image_placeholder)
                    .into(binding.postImageView)
            }
        }
    }
}









