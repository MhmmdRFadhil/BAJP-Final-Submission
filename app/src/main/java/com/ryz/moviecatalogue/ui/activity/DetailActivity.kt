package com.ryz.moviecatalogue.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.databinding.ActivityDetailBinding
import com.ryz.moviecatalogue.model.CatalogueEnitity
import com.ryz.moviecatalogue.utils.loadImageUrl

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val catalogueId = extras.getInt(EXTRA_ID)
            val category = extras.getString(EXTRA_CATEGORY)

            if (category != null) {
                viewModel.setCatalogue(catalogueId, category)
                val catalogue = viewModel.getCatalogue()
                populateDataDetail(catalogue)
                supportActionBar?.apply {
                    title = catalogue.title
                    setDisplayHomeAsUpEnabled(true)
                }
            }
        }
    }

    private fun populateDataDetail(catalogue: CatalogueEnitity) {
        binding.apply {
            posterDetail.loadImageUrl(catalogue.poster)
            tvTitleDetail.text = catalogue.title
            tvUserScoreDetail.text = catalogue.score
            tvGenreDetail.text = catalogue.genre
            tvDurationDetail.text = catalogue.duration
            tvYearDetail.text = catalogue.years
            tvOverviewDetail.text = catalogue.overview
        }
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