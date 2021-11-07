package com.ryz.moviecatalogue.ui.activity

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.ui.content.movie.MovieFragment
import com.ryz.moviecatalogue.ui.content.tvshow.TvShowFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }
        return fragment as Fragment
    }

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.menu_movies, R.string.menu_tv_show)
    }

}