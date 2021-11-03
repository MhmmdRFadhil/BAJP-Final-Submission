package com.ryz.moviecatalogue.ui.fragment.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryz.moviecatalogue.databinding.FragmentMovieBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.model.CatalogueEnitity
import com.ryz.moviecatalogue.ui.activity.DetailActivity
import com.ryz.moviecatalogue.ui.activity.DetailViewModel.Companion.MOVIE

class MovieFragment : Fragment() {
    private var binding: FragmentMovieBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = viewModel.getMovie()

            val movieAdapter = MoviesAdapter()
            movieAdapter.setMovie(movies)

            binding?.rvMovie?.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            movieAdapter.setOnItemClickedCallback(object : ItemClickedCallback {
                override fun onItemClick(catalogueEnitity: CatalogueEnitity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, catalogueEnitity.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}