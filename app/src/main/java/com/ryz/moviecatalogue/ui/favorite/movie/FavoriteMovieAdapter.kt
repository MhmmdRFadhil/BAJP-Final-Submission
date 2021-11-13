package com.ryz.moviecatalogue.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.databinding.ItemRowCatalogueBinding
import com.ryz.moviecatalogue.utils.loadImageUrl

class FavoriteMovieAdapter :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickedCallback: FavMovieClickedCallback


    fun setOnItemClickedCallback(onItemClickedCallback: FavMovieClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    inner class ViewHolder(private val binding: ItemRowCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            with(binding) {
                tvTitleDetail.text = movieEntity.title
                movieEntity.poster?.let { imgPosterItem.loadImageUrl(it) }
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(movieEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRowCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catalogue = getItem(position)
        if (catalogue != null) {
            holder.bind(catalogue)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean = oldItem == newItem

        }
    }
}

interface FavMovieClickedCallback {
    fun onItemClick(movieEntity: MovieEntity)
}
