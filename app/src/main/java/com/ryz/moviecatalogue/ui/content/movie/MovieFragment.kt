package com.ryz.moviecatalogue.ui.content.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ryz.moviecatalogue.databinding.FragmentMovieBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.ui.activity.DetailActivity
import com.ryz.moviecatalogue.ui.activity.DetailViewModel.Companion.TYPE_MOVIE
import com.ryz.moviecatalogue.ui.content.CatalogueAdapter
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory

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
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            binding?.progressBar?.visibility = View.VISIBLE
            val movieAdapter = CatalogueAdapter()
            viewModel.getMovieNowPlaying().observe(viewLifecycleOwner, {
                binding?.progressBar?.visibility = View.GONE
                movieAdapter.setCatalogue(it)
            })

            binding?.rvMovie?.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            movieAdapter.setOnItemClickedCallback(object : ItemClickedCallback {
                override fun onItemClick(catalogueEntity: CatalogueEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, catalogueEntity.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, TYPE_MOVIE)
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