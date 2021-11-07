package com.ryz.moviecatalogue.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.databinding.ItemRowCatalogueBinding
import com.ryz.moviecatalogue.utils.MyDiffUtils
import com.ryz.moviecatalogue.utils.loadImageUrl

class CatalogueAdapter : RecyclerView.Adapter<CatalogueAdapter.ViewHolder>() {

    private lateinit var onItemClickedCallback: ItemClickedCallback
    private var listCatalogue = ArrayList<CatalogueEntity>()

    fun setOnItemClickedCallback(onItemClickedCallback: ItemClickedCallback) {
        this.onItemClickedCallback = onItemClickedCallback
    }

    fun setCatalogue(catalogueEntity: List<CatalogueEntity>) {
        val myDiffUtils = MyDiffUtils(listCatalogue, catalogueEntity)
        val diffResult = DiffUtil.calculateDiff(myDiffUtils)
        listCatalogue.clear()
        listCatalogue.addAll(catalogueEntity)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ItemRowCatalogueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(catalogueEntity: CatalogueEntity) {
            with(binding) {
                tvTitleDetail.text = catalogueEntity.title
                catalogueEntity.poster?.let { imgPosterItem.loadImageUrl(it) }
            }

            itemView.setOnClickListener {
                onItemClickedCallback.onItemClick(catalogueEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRowCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCatalogue[position])
    }

    override fun getItemCount(): Int = listCatalogue.size
}