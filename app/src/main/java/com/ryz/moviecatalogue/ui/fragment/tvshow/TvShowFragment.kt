package com.ryz.moviecatalogue.ui.fragment.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ryz.moviecatalogue.databinding.FragmentTvShowBinding
import com.ryz.moviecatalogue.interfaces.ItemClickedCallback
import com.ryz.moviecatalogue.model.CatalogueEnitity
import com.ryz.moviecatalogue.ui.activity.DetailActivity
import com.ryz.moviecatalogue.ui.activity.DetailViewModel.Companion.TV

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
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val tvShow = viewModel.getTvShow()
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShow(tvShow)

            binding?.rvTvShow?.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            tvShowAdapter.setOnItemClickedCallback(object : ItemClickedCallback {
                override fun onItemClick(catalogueEnitity: CatalogueEnitity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, catalogueEnitity.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV)
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