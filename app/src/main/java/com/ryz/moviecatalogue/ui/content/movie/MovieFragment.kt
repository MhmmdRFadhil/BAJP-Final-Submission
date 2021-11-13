package com.ryz.moviecatalogue.ui.content.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.data.source.local.entity.MovieEntity
import com.ryz.moviecatalogue.databinding.FragmentMovieBinding
import com.ryz.moviecatalogue.ui.activity.MainActivity
import com.ryz.moviecatalogue.ui.detail.DetailActivity
import com.ryz.moviecatalogue.ui.detail.DetailViewModel
import com.ryz.moviecatalogue.utils.SortUtils.TITLE_ASCENDING
import com.ryz.moviecatalogue.utils.SortUtils.TITLE_DESCENDING
import com.ryz.moviecatalogue.utils.SortUtils.TITLE_RANDOM
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory
import com.ryz.moviecatalogue.vo.Resource
import com.ryz.moviecatalogue.vo.Status

class MovieFragment : Fragment() {

    private var binding: FragmentMovieBinding? = null
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.title_movie)
            showLoading(true)
            setViewModel()
        }
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        movieViewModel =
            ViewModelProvider(this, factory)[MovieViewModel::class.java]
        movieViewModel.getMovieNowPlaying(TITLE_ASCENDING)
            .observe(viewLifecycleOwner, movieObserver)
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movie ->
        if (movie != null) {
            when (movie.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    showRecyclerView(movie.data)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showRecyclerView(dataMovie: PagedList<MovieEntity>?) {
        binding?.rvMovie?.apply {
            movieAdapter = MovieAdapter()
            setHasFixedSize(true)
            movieAdapter.submitList(dataMovie)

            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter

            movieAdapter.setOnItemClickedCallback(object : MovieClickedCallback {
                override fun onItemClick(movieEntity: MovieEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movieEntity.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, DetailViewModel.TYPE_MOVIE)
                    startActivity(intent)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        binding?.apply {
            progressBar.isVisible = state
            rvMovie.isInvisible = state
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_sort, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_title_ascending -> {
                sort = TITLE_ASCENDING
                Toast.makeText(
                    context,
                    getString(R.string.sort_movie_ascending),
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.action_title_descending -> {
                sort = TITLE_DESCENDING
                Toast.makeText(
                    context,
                    getString(R.string.sort_movie_descending),
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.action_title_random -> {
                sort = TITLE_RANDOM
                Toast.makeText(context, getString(R.string.sort_movie_random), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        movieViewModel.getMovieNowPlaying(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

}