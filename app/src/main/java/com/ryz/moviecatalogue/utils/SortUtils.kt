package com.ryz.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val TITLE_DESCENDING = "title_descending"
    const val TITLE_ASCENDING = "title_ascending"
    const val TITLE_RANDOM = "random"
    const val MOVIE_ENTITIES = "movie_entities"
    const val TV_SHOW_ENTITIES = "tv_show_entities"

    fun getSortedQuery(filter: String, tableName: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $tableName ")
        when (filter) {
            TITLE_ASCENDING -> simpleQuery.append("ORDER BY title ASC")
            TITLE_DESCENDING -> simpleQuery.append("ORDER BY title DESC")
            TITLE_RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}