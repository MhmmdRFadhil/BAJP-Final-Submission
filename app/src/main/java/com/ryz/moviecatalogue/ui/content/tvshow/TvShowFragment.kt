package com.ryz.moviecatalogue.ui.content.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ryz.moviecatalogue.data.source.entity.CatalogueEntity
import com.ryz.moviecatalogue.databinding.FragmentTvShowBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.ui.activity.DetailActivity
import com.ryz.moviecatalogue.ui.activity.DetailViewModel.Companion.TYPE_TV_SHOW
import com.ryz.moviecatalogue.ui.content.CatalogueAdapter
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private var binding: FragmentTvShowBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            binding?.progressBar?.visibility = View.VISIBLE
            val tvShowAdapter = CatalogueAdapter()
            viewModel.getTvShowAiringToday().observe(viewLifecycleOwner, {
                binding?.progressBar?.visibility = View.GONE
                tvShowAdapter.setCatalogue(it)
            })

            binding?.rvTvShow?.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            tvShowAdapter.setOnItemClickedCallback(object : ItemClickedCallback {
                override fun onItemClick(catalogueEntity: CatalogueEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, catalogueEntity.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, TYPE_TV_SHOW)
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