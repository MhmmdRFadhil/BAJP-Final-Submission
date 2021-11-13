package com.ryz.moviecatalogue.ui.content.tvshow

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
import com.ryz.moviecatalogue.data.source.local.entity.TvShowEntity
import com.ryz.moviecatalogue.databinding.FragmentTvShowBinding
import com.ryz.moviecatalogue.ui.activity.MainActivity
import com.ryz.moviecatalogue.ui.detail.DetailActivity
import com.ryz.moviecatalogue.ui.detail.DetailViewModel.Companion.TYPE_TV_SHOW
import com.ryz.moviecatalogue.viewmodel.ViewModelFactory
import com.ryz.moviecatalogue.vo.Resource
import com.ryz.moviecatalogue.vo.Status
import com.ryz.moviecatalogue.utils.SortUtils.TITLE_ASCENDING
import com.ryz.moviecatalogue.utils.SortUtils.TITLE_DESCENDING
import com.ryz.moviecatalogue.utils.SortUtils.TITLE_RANDOM

class TvShowFragment : Fragment() {
    private var binding: FragmentTvShowBinding? = null
    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = "TV Show Catalogue"
            showLoading(true)
            setViewModel()
        }
    }

    private fun setViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        tvShowViewModel =
            ViewModelProvider(this, factory)[TvShowViewModel::class.java]
        tvShowViewModel.getTvShowAiringToday(TITLE_ASCENDING)
            .observe(viewLifecycleOwner, tvShowObserver)
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    showRecyclerView(tvShow.data)
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(context, getString(R.string.error_message), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showRecyclerView(dataTvShow: PagedList<TvShowEntity>?) {
        binding?.rvTvShow?.apply {
            tvShowAdapter = TvShowAdapter()
            setHasFixedSize(true)
            tvShowAdapter.submitList(dataTvShow)

            layoutManager = GridLayoutManager(context, 2)
            adapter = tvShowAdapter

            tvShowAdapter.setOnItemClickedCallback(object : TvShowClickedCallback {
                override fun onItemClick(tvShowEntity: TvShowEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tvShowEntity.id)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, TYPE_TV_SHOW)
                    startActivity(intent)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        binding?.apply {
            progressBar.isVisible = state
            rvTvShow.isInvisible = state
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.item_sort, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_title_ascending -> {
                sort = TITLE_ASCENDING
                Toast.makeText(
                    context,
                    getString(R.string.sort_tv_show_ascending),
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.action_title_descending -> {
                sort = TITLE_DESCENDING
                Toast.makeText(
                    context,
                    getString(R.string.sort_tv_show_descending),
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.action_title_random -> {
                sort = TITLE_RANDOM
                Toast.makeText(context, getString(R.string.sort_tv_show_random), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        tvShowViewModel.getTvShowAiringToday(sort).observe(viewLifecycleOwner, tvShowObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)

    }
}