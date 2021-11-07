package com.ryz.moviecatalogue.utils

import androidx.recyclerview.widget.DiffUtil
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity

class MyDiffUtils(
    private val oldList: List<CatalogueEntity>,
    private val newList: List<CatalogueEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }

            oldList[oldItemPosition].poster != newList[newItemPosition].poster -> {
                false
            }

            else -> true
        }
    }
}