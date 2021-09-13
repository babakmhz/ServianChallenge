package com.babakmhz.servianchallenge.ui.base.main

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.babakmhz.servianchallenge.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.matches

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @Test
    fun testUsersFragmentIsVisibleAsEntryPoint(){
        val activityRule = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(5000)
        onView(withId(R.id.root_users)).check(ViewAssertions.matches(isDisplayed()))
    }



    @Test
    fun testClickingOnRecyclerViewShouldNavigateToAlbumsFragment(){
        val activityRule = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(10000)
        onView(withId(R.id.rcl_users)).perform(RecyclerViewActions.actionOnItemAtPosition<UsersRecyclerViewAdapter.ViewHolder>(0,click()))
        onView(withId(R.id.root_album)).check(ViewAssertions.matches(isDisplayed()))
    }
    @Test
    fun testClickingOnRecyclerViewShouldNavigateToPhotosFragment(){
        val activityRule = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(10000)
        onView(withId(R.id.rcl_users)).perform(RecyclerViewActions.actionOnItemAtPosition<UsersRecyclerViewAdapter.ViewHolder>(0,click()))
        onView(withId(R.id.root_album)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rcl_album)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumItemRecyclerViewAdapter.ViewHolder>(0,click()))
        onView(withId(R.id.root_photo)).check(ViewAssertions.matches(isDisplayed()))
    }
}