package com.ryz.moviecatalogue.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.databinding.ActivityDetailBinding
import com.ryz.moviecatalogue.data.source.entity.DetailEntity
import com.ryz.moviecatalogue.utils.loadImageUrl
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory
import kotlin.math.abs

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        showLoading(true)

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getInt(EXTRA_ID)
            val dataCategory = extras.getString(EXTRA_CATEGORY)
            if (dataCategory != null) {
                viewModel.setCatalogue(dataId, dataCategory)
                viewModel.getCatalogue().observe(this, {
                    populateDataDetail(it)
                    supportActionBar?.apply {
                        title = it.title
                        setDisplayHomeAsUpEnabled(true)
                    }
                })
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
            nestedScrollview.isInvisible = isLoading
        }
    }

    private fun populateDataDetail(detail: DetailEntity) {

        binding.apply {
            detail.poster?.let { posterDetail.loadImageUrl(it) }
            tvTitleDetail.text = detail.title
            tvUserScoreDetail.text = detail.score.toString()

            tvGenreDetail.text = convertGenre(detail.genre)
            tvDurationDetail.text = convertDuration(detail.duration)
            tvYearDetail.text = convertYears(detail.years)
            tvOverviewDetail.text = convertOverview(detail.overview)

            showLoading(false)
        }
    }

    private fun convertGenre(genre: List<String>?): String {
        var tvGenre = ""

        genre?.let {
            tvGenre = if (it.isEmpty()) {
                getString(R.string.no_info)
            } else {
                it.toString().replace("[", "").replace("]", "")
            }
        }
        return tvGenre
    }

    private fun convertYears(years: String?): String {
        val date = years?.split("-")
        return date?.get(0) ?: getString(R.string.unknown)
    }

    private fun convertDuration(duration: Int?): String {
        var tvDuration = ""

        duration?.let {
            val hours = it / 60
            val minutes = abs(it) % 60

            tvDuration = when {
                hours == 0 && minutes == 0 -> {
                    getString(R.string.unknown)
                }
                hours == 0 -> {
                    "$minutes m"
                }
                minutes == 0 -> {
                    "$hours h"
                }
                else -> {
                    "$hours h $minutes m"
                }
            }
        }
        return tvDuration
    }

    private fun convertOverview(overview: String?): String {
        var tvOverview = ""

        overview?.let {
            tvOverview = if (it.isEmpty()) {
                getString(R.string.no_info)
            } else {
                it
            }
        }
        return tvOverview
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val intent = Intent()
                intent.apply {
                    action = Intent.ACTION_SEND
                    binding.apply {
                        putExtra(
                            Intent.EXTRA_TEXT,
                            """
                                ${getString(R.string.title_catalogue)} : ${tvTitleDetail.text}
                                ${getString(R.string.genre_catalogue)} : ${tvGenreDetail.text}
                                ${getString(R.string.user_score_catalogue)} : ${tvUserScoreDetail.text}
                                ${getString(R.string.duration_catalogue)} : ${tvDurationDetail.text}
                                ${getString(R.string.years_catalogue)} : ${tvYearDetail.text}
                                ${getString(R.string.overview_catalogue)} : ${tvOverviewDetail.text}
                            """.trimIndent()
                        )
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(intent, getString(R.string.share)))
                }
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }
}