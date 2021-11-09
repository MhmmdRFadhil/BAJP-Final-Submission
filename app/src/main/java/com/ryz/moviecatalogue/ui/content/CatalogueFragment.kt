package com.ryz.moviecatalogue.ui.content

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.databinding.FragmentCatalogueBinding
import com.ryz.moviecatalogue.ui.activity.DetailActivity
import com.ryz.moviecatalogue.ui.activity.DetailViewModel.Companion.TYPE_MOVIE
import com.ryz.moviecatalogue.ui.activity.DetailViewModel.Companion.TYPE_TV_SHOW
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory

class CatalogueFragment : Fragment() {
    private var binding: FragmentCatalogueBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogueBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val catalogueViewModel = ViewModelProvider(this, factory)[CatalogueViewModel::class.java]

            binding?.progressBar?.visibility = View.VISIBLE
            val catalogueAdapter = CatalogueAdapter()
            if (arguments?.getInt(ARG_SECTION_NUMBER, 0) == 0) {
                catalogueViewModel.getMovieNowPlaying().observe(viewLifecycleOwner, {
                    binding?.progressBar?.visibility = View.GONE
                    catalogueAdapter.setCatalogue(it)
                })
                catalogueAdapter.setOnItemClickedCallback(object : ItemClickedCallback {
                    override fun onItemClick(catalogueEntity: CatalogueEntity) {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_ID, catalogueEntity.id)
                        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TYPE_MOVIE)
                        startActivity(intent)
                    }
                })
            } else {
                catalogueViewModel.getTvShowAiringToday().observe(viewLifecycleOwner, {
                    binding?.progressBar?.visibility = View.GONE
                    catalogueAdapter.setCatalogue(it)
                })
                catalogueAdapter.setOnItemClickedCallback(object : ItemClickedCallback {
                    override fun onItemClick(catalogueEntity: CatalogueEntity) {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra(DetailActivity.EXTRA_ID, catalogueEntity.id)
                        intent.putExtra(DetailActivity.EXTRA_CATEGORY, TYPE_TV_SHOW)
                        startActivity(intent)
                    }
                })
            }

            binding?.rvMovie?.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = catalogueAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) = CatalogueFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_SECTION_NUMBER, index)
            }
        }
    }
}