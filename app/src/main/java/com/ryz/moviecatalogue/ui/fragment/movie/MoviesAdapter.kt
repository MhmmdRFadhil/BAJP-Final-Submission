package com.ryz.moviecatalogue.ui.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.databinding.ItemRowMoviesBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.model.CatalogueEntity
import com.ryz.moviecatalogue.utils.loadImageUrl

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var onItemClickedCallback: ItemClickedCallback
    private var listMovie = ArrayList<CatalogueEntity>()

    fun setOnItemClickedCallback(onItemClickedCallback: ItemClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    fun setMovie(catalogueEntity: List<CatalogueEntity>?) {
        if (catalogueEntity == null) return
        this.listMovie.clear()
        this.listMovie.addAll(catalogueEntity)
    }

    inner class ViewHolder(private val binding: ItemRowMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (catalogueEntity: CatalogueEntity) {
            with(binding) {
                tvItemTitleMovie.text = catalogueEntity.title
                tvItemGenreMovie.text = catalogueEntity.genre
                tvItemDurationMovie.text = catalogueEntity.duration
                posterMovie.loadImageUrl(catalogueEntity.poster)
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(catalogueEntity)
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