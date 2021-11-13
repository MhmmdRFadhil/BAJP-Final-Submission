package com.ryz.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.databinding.FragmentFavoriteBinding
import com.ryz.moviecatalogue.ui.activity.MainActivity
import com.ryz.moviecatalogue.ui.viewpager.FavoriteSectionPagerAdapter

class FavoriteFragment : Fragment() {
    private var binding: FragmentFavoriteBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as MainActivity).supportActionBar?.title = getString(R.string.title_favorite)
            val pagerAdapter = FavoriteSectionPagerAdapter(context as AppCompatActivity)
            binding?.let {
                it.viewPager.adapter = pagerAdapter
                TabLayoutMediator(it.tabs, it.viewPager) { tabs, position ->
                    tabs.text =
                        resources.getString(FavoriteSectionPagerAdapter.TAB_TITLES[position])
                }.attach()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_sort).isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}