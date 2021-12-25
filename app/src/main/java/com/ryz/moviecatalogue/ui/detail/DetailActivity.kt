package com.ryz.moviecatalogue.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.databinding.ActivityDetailBinding
import com.ryz.moviecatalogue.ui.detail.DetailViewModel.Companion.TYPE_MOVIE
import com.ryz.moviecatalogue.ui.detail.DetailViewModel.Companion.TYPE_TV_SHOW
import com.ryz.moviecatalogue.utils.loadImageUrl
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory
import com.ryz.moviecatalogue.vo.Status
import kotlin.math.abs

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var dataCategory: String? = null
    private var isMovieDetail: Boolean = false
    private lateinit var movieEntity: MovieEntity
    private lateinit var tvShowEntity: TvShowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        showLoading(true)
        binding.fabAddFavorite.setOnClickListener(this)

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getInt(EXTRA_ID)
            dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataCategory != null) {
                if (dataCategory == TYPE_MOVIE) {
                    isMovieDetail = true
                    viewModel.setCatalogue(dataId, dataCategory.toString())
                    viewModel.getDetailMovie().observe(this, { detailMovie ->
                        when (detailMovie.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (detailMovie.data != null) {
                                    showLoading(false)
                                    populateDataDetailMovie(detailMovie.data)
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.error_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                } else if (dataCategory == TYPE_TV_SHOW) {
                    isMovieDetail = false
                    viewModel.setCatalogue(dataId, dataCategory.toString())
                    viewModel.getDetailTvShow().observe(this, { detailTvShow ->
                        when (detailTvShow.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                if (detailTvShow.data != null) {
                                    showLoading(false)
                                    populateDataDetailTvShow(detailTvShow.data)
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(
                                    applicationContext,
                                    getString(R.string.error_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            progressBar.isVisible = state
            nestedScrollview.isInvisible = state
            fabAddFavorite.isInvisible = state
        }
    }

    private fun populateDataDetailMovie(detail: MovieEntity) {
        movieEntity = detail
        setFavState(detail.isFav)
        binding.apply {
            detail.poster?.let { posterDetail.loadImageUrl(it) }
            tvTitleDetail.text = detail.title
            tvUserScoreDetail.text = detail.score.toString()

            tvGenreDetail.text = detail.genre
            tvDurationDetail.text = convertDuration(detail.duration)
            tvYearDetail.text = convertYears(detail.years)
            tvOverviewDetail.text = convertOverview(detail.overview)


            supportActionBar?.apply {
                title = detail.title
                setDisplayHomeAsUpEnabled(true)
            }

            showLoading(false)
        }
    }


    private fun populateDataDetailTvShow(detail: TvShowEntity) {
        tvShowEntity = detail
        setFavState(detail.isFav)
        binding.apply {
            detail.poster?.let { posterDetail.loadImageUrl(it) }
            tvTitleDetail.text = detail.title
            tvUserScoreDetail.text = detail.score.toString()

            tvGenreDetail.text = detail.genre
            tvDurationDetail.text = convertDuration(detail.duration)
            tvYearDetail.text = convertYears(detail.years)
            tvOverviewDetail.text = convertOverview(detail.overview)

            supportActionBar?.apply {
                title = detail.title
                setDisplayHomeAsUpEnabled(true)
            }
            showLoading(false)
        }
    }

    private fun setFavState(isFavorite: Boolean) {
        binding.apply {
            if (isFavorite) fabAddFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            else fabAddFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
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
        menuInflater.inflate(R.menu.item_share, menu)
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fabAddFavorite -> {
                if (isMovieDetail) {
                    if (!movieEntity.isFav)
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.movie_successfully_added),
                            Toast.LENGTH_SHORT
                        ).show()
                    else Toast.makeText(
                        applicationContext,
                        getString(R.string.movie_successfully_removed),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.setFavoriteMovie(movieEntity)
                } else {
                    if (!tvShowEntity.isFav)
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.tv_show_successfully_added),
                            Toast.LENGTH_SHORT
                        ).show()
                    else Toast.makeText(
                        applicationContext,
                        getString(R.string.tv_show_successfully_removed),
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.setFavoriteTvShow(tvShowEntity)
                }
            }
        }
    }


    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_CATEGORY = "extra_category"
    }
}