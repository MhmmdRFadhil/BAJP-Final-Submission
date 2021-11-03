package com.ryz.moviecatalogue.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy.getMovie()
    private val dummyTvShow = DataDummy.getTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rvMovie)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
        }
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvTitleDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovies[0].title)))
        }

        onView(withId(R.id.tvGenreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovies[0].genre)))
        }

        onView(withId(R.id.tvUserScoreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovies[0].score)))
        }

        onView(withId(R.id.tvDurationDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovies[0].duration)))
        }

        onView(withId(R.id.tvYearDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovies[0].years)))
        }

        onView(withId(R.id.tvOverviewDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyMovies[0].overview)))
        }
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rvTvShow)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
        }
    }

    @Test
    fun loadTvShowDetail() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvTitleDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyTvShow[0].title)))
        }

        onView(withId(R.id.tvGenreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyTvShow[0].genre)))
        }

        onView(withId(R.id.tvUserScoreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyTvShow[0].score)))
        }

        onView(withId(R.id.tvDurationDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyTvShow[0].duration)))
        }

        onView(withId(R.id.tvYearDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyTvShow[0].years)))
        }

        onView(withId(R.id.tvOverviewDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyTvShow[0].overview)))
        }
    }
}