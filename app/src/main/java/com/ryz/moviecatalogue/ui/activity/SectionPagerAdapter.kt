package com.ryz.moviecatalogue.ui.activity

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.ui.content.CatalogueFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        return CatalogueFragment.newInstance(position)
    }

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.menu_movies, R.string.menu_tv_show)
    }
}