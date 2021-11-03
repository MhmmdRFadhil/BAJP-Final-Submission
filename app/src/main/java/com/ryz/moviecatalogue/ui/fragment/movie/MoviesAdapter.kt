package com.ryz.moviecatalogue.ui.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.databinding.ItemRowMoviesBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.model.CatalogueEnitity
import com.ryz.moviecatalogue.utils.loadImageUrl

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var onItemClickedCallback: ItemClickedCallback
    private var listMovie = ArrayList<CatalogueEnitity>()

    fun setOnItemClickedCallback(onItemClickedCallback: ItemClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    fun setMovie(catalogueEnitity: List<CatalogueEnitity>?) {
        if (catalogueEnitity == null) return
        this.listMovie.clear()
        this.listMovie.addAll(catalogueEnitity)
    }

    inner class ViewHolder(private val binding: ItemRowMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (catalogueEnitity: CatalogueEnitity) {
            with(binding) {
                tvItemTitleMovie.text = catalogueEnitity.title
                tvItemGenreMovie.text = catalogueEnitity.genre
                tvItemDurationMovie.text = catalogueEnitity.duration
                posterMovie.loadImageUrl(catalogueEnitity.poster)
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(catalogueEnitity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size
}