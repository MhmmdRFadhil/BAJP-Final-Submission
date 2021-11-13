package com.ryz.moviecatalogue.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.databinding.FragmentFavoriteCatalogueBinding
import com.ryz.moviecatalogue.ui.detail.DetailActivity
import com.ryz.moviecatalogue.ui.detail.DetailViewModel
import com.ryz.moviecatalogue.ui.favorite.movie.FavMovieClickedCallback
import com.ryz.moviecatalogue.ui.favorite.movie.FavoriteMovieAdapter
import com.ryz.moviecatalogue.ui.favorite.tvshow.FavTvShowClickedCallback
import com.ryz.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowAdapter
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteCatalogueFragment : Fragment() {
    private var binding: FragmentFavoriteCatalogueBinding? = null
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter
    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteCatalogueBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            setViewModel()
        }
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        if (arguments?.getInt(ARG_SECTION_NUMBER, 0) == 0) {
            favoriteMovieAdapter = FavoriteMovieAdapter()
            favoriteViewModel.getFavMovie().observe(viewLifecycleOwner, { favMovie ->
                if (favMovie.size == 0) {
                    emptyDataMovie()
                } else {
                    showRecyclerViewMovie(favMovie)
                }
                showLoading(false)
            })
            favoriteMovieAdapter.setOnItemClickedCallback(object : FavMovieClickedCallback {
                override fun onItemClick(movieEntity: MovieEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movieEntity.id)
                    intent.putExtra(
                        DetailActivity.EXTRA_CATEGORY,
                        DetailViewModel.TYPE_MOVIE
                    )
                    startActivity(intent)
                }
            })
        } else {
            favoriteTvShowAdapter = FavoriteTvShowAdapter()
            favoriteViewModel.getFavTvShow().observe(viewLifecycleOwner, { favTvShow ->
                if (favTvShow.size == 0) {
                    emptyDataTvShow()
                } else {
                    showRecyclerViewTvShow(favTvShow)
                }
                showLoading(false)
            })
            favoriteTvShowAdapter.setOnItemClickedCallback(object :
                FavTvShowClickedCallback {
                override fun onItemClick(tvShowEntity: TvShowEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShowEntity.id)
                    intent.putExtra(
                        DetailActivity.EXTRA_CATEGORY,
                        DetailViewModel.TYPE_TV_SHOW
                    )
                    startActivity(intent)
                }
            })

        }
    }

    private fun emptyDataTvShow() {
        binding?.apply {
            rvFavoriteCatalogue.visibility = View.GONE
            layoutEmptyData.let {
                it.tvTitleEmptyData.text = getString(R.string.title_result_empty_data_tv_show)
                it.tvSubTitleEmptyData.text = getString(R.string.subtitle_result_empty_data_tv_show)
                it.root.visibility = View.VISIBLE
            }
        }
    }

    private fun emptyDataMovie() {
        binding?.apply {
            rvFavoriteCatalogue.visibility = View.GONE
            layoutEmptyData.let {
                it.tvTitleEmptyData.text = getString(R.string.title_result_empty_data_movie)
                it.tvSubTitleEmptyData.text = getString(R.string.subtitle_result_empty_data_movie)
                it.root.visibility = View.VISIBLE
            }
        }
    }

    private fun showRecyclerViewTvShow(favTvShow: PagedList<TvShowEntity>?) {
        binding?.apply {
            rvFavoriteCatalogue.setHasFixedSize(true)
            favoriteTvShowAdapter.submitList(favTvShow)

            rvFavoriteCatalogue.layoutManager = GridLayoutManager(context, 2)
            rvFavoriteCatalogue.adapter = favoriteTvShowAdapter
        }
    }

    private fun showRecyclerViewMovie(favMovie: PagedList<MovieEntity>?) {
        binding?.apply {
            rvFavoriteCatalogue.setHasFixedSize(true)
            favoriteMovieAdapter.submitList(favMovie)

            rvFavoriteCatalogue.layoutManager = GridLayoutManager(context, 2)
            rvFavoriteCatalogue.adapter = favoriteMovieAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        binding?.apply {
            progressBar.isVisible = state
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) =
            FavoriteCatalogueFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }
}