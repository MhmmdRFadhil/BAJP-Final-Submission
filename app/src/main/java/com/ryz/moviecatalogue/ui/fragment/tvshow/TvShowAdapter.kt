package com.ryz.moviecatalogue.ui.fragment.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.databinding.ItemRowTvShowBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.model.CatalogueEntity
import com.ryz.moviecatalogue.utils.loadImageUrl

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private lateinit var onItemClickedCallback: ItemClickedCallback
    private val listTvShow = ArrayList<CatalogueEntity>()

    fun setOnItemClickedCallback(onItemClickedCallback: ItemClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    fun setTvShow(catalogueEntity: List<CatalogueEntity>?) {
        if (catalogueEntity == null) return
        listTvShow.clear()
        listTvShow.addAll(catalogueEntity)
    }

    inner class ViewHolder(private val binding: ItemRowTvShowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (catalogueEntity: CatalogueEntity) {
            with(binding) {
                tvItemTitleTvShow.text = catalogueEntity.title
                tvItemGenreTvShow.text = catalogueEntity.genre
                tvItemDurationTvShow.text = catalogueEntity.duration
                posterTvShow.loadImageUrl(catalogueEntity.poster)
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(catalogueEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size
}