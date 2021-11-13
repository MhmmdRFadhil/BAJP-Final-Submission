package com.ryz.moviecatalogue.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.databinding.ItemRowCatalogueBinding
import com.ryz.moviecatalogue.utils.loadImageUrl

class FavoriteTvShowAdapter :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickedCallback: FavTvShowClickedCallback

    fun setOnItemClickedCallback(onItemClickedCallback: FavTvShowClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    inner class ViewHolder(private val binding: ItemRowCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(binding) {
                tvTitleDetail.text = tvShowEntity.title
                tvShowEntity.poster?.let { imgPosterItem.loadImageUrl(it) }
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(tvShowEntity)
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(
                oldItem: TvShowEntity,
                newItem: TvShowEntity
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TvShowEntity,
                newItem: TvShowEntity
            ): Boolean = oldItem == newItem

        }
    }
}

interface FavTvShowClickedCallback {
    fun onItemClick(tvShowEntity: TvShowEntity)
}