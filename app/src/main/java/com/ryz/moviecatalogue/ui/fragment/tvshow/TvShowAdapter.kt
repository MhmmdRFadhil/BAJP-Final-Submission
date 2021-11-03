package com.ryz.moviecatalogue.ui.fragment.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.databinding.ItemRowTvShowBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.model.CatalogueEnitity
import com.ryz.moviecatalogue.utils.loadImageUrl

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private lateinit var onItemClickedCallback: ItemClickedCallback
    private val listTvShow = ArrayList<CatalogueEnitity>()

    fun setOnItemClickedCallback(onItemClickedCallback: ItemClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    fun setTvShow(catalogueEnitity: List<CatalogueEnitity>?) {
        if (catalogueEnitity == null) return
        listTvShow.clear()
        listTvShow.addAll(catalogueEnitity)
    }

    inner class ViewHolder(private val binding: ItemRowTvShowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (catalogueEnitity: CatalogueEnitity) {
            with(binding) {
                tvItemTitleTvShow.text = catalogueEnitity.title
                tvItemGenreTvShow.text = catalogueEnitity.genre
                tvItemDurationTvShow.text = catalogueEnitity.duration
                posterTvShow.loadImageUrl(catalogueEnitity.poster)
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(catalogueEnitity)
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