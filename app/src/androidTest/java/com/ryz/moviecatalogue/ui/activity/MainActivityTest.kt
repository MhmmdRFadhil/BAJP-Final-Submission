package com.ryz.moviecatalogue.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ryz.moviecatalogue.R
import com.ryz.moviecatalogue.utils.DataDummy
import com.ryz.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.abs

class MainActivityTest {
    private val dummyMovies = DataDummy.getMovie()
    private val dummyTvShow = DataDummy.getTvShow()
    private val dummyDetailMovie = DataDummy.getMovieDetail()
    private val dummyDetailTvShow = DataDummy.getTvShowDetail()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

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
            check(matches(withText(dummyDetailMovie.title)))
        }

        onView(withId(R.id.tvGenreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertGenre(dummyDetailMovie.genre))))
        }

        onView(withId(R.id.tvUserScoreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyDetailMovie.score.toString())))
        }

        onView(withId(R.id.tvDurationDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertDuration(dummyDetailMovie.duration))))
        }

        onView(withId(R.id.tvYearDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertYears(dummyDetailMovie.years))))
        }

        onView(withId(R.id.tvOverviewDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertOverview(dummyDetailMovie.overview))))
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
            check(matches(withText(dummyDetailTvShow.title)))
        }

        onView(withId(R.id.tvGenreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertGenre(dummyDetailTvShow.genre))))
        }

        onView(withId(R.id.tvUserScoreDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(dummyDetailTvShow.score.toString())))
        }

        onView(withId(R.id.tvDurationDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertDuration(dummyDetailTvShow.duration))))
        }

        onView(withId(R.id.tvYearDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertYears(dummyDetailTvShow.years))))
        }

        onView(withId(R.id.tvOverviewDetail)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(convertOverview(dummyDetailTvShow.overview))))
        }
    }

    private fun convertOverview(overview: String?): String {
        var tvOverview = ""

        overview?.let {
            tvOverview = if (it.isEmpty()) {
                "No info"
            } else {
                it
            }
        }
        return tvOverview
    }

    private fun convertDuration(duration: Int?): String {
        var tvDuration = ""
        duration?.let {
            val hours = it / 60
            val minutes = abs(it) % 60

            tvDuration = when {
                hours == 0 && minutes == 0 -> {
                    "-"
                }
                hours == 0 -> {
                    "$minutes m"
                }

                hours == 0 -> {
                    "$hours h"
                }

                else -> {
                    "$hours h $minutes m"
                }
            }
        }
        return tvDuration
    }


    private fun convertGenre(genre: List<String>?): String {
        var tvGenre = ""
        genre?.let {
            tvGenre = if (it.isEmpty()) {
                "No info"
            } else {
                it.toString().replace("[", "").replace("]", "")
            }
        }
        return tvGenre
    }

    private fun convertYears(years: String?): String {
        val date = years?.split("-")
        return date?.get(0) ?: "-"
    }
}