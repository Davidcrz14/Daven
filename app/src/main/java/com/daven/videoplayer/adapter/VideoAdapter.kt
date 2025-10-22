package com.daven.videoplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daven.videoplayer.R
import com.daven.videoplayer.databinding.ItemVideoBinding
import com.daven.videoplayer.model.Video

class VideoAdapter(
    private val onVideoClick: (Video) -> Unit,
    private val onFavoriteClick: (Video) -> Unit,
    private val onMoreClick: (Video) -> Unit
) : ListAdapter<Video, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VideoViewHolder(
        private val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.apply {
                videoTitle.text = video.title
                videoFolder.text = video.folder
                videoSize.text = video.getFormattedSize()
                durationText.text = video.getFormattedDuration()

                // Load thumbnail
                Glide.with(videoThumbnail.context)
                    .load(video.uri)
                    .placeholder(R.drawable.ic_video_library)
                    .error(R.drawable.ic_video_library)
                    .centerCrop()
                    .into(videoThumbnail)

                // Update favorite icon
                favoriteIcon.setImageResource(
                    if (video.isFavorite) R.drawable.ic_favorite
                    else R.drawable.ic_favorite_border
                )

                // Click listeners
                root.setOnClickListener { onVideoClick(video) }
                favoriteIcon.setOnClickListener { onFavoriteClick(video) }
                moreOptions.setOnClickListener { onMoreClick(video) }
            }
        }
    }

    private class VideoDiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }
    }
}
