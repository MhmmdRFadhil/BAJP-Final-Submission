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

class MainActivityTest {
    private val dummyMovies = DataDummy.getMovie()
    private val dummyTvShow = DataDummy.getTvShow()

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
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvGenreDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvUserScoreDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDurationDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvYearDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvOverviewDetail)).check(matches(isDisplayed()))

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
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvGenreDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvUserScoreDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDurationDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvYearDetail)).check(matches(isDisplayed()))

        onView(withId(R.id.tvOverviewDetail)).check(matches(isDisplayed()))

    }
}