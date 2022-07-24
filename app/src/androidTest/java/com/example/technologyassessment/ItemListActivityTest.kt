package com.example.technologyassessment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ItemListActivityTest {

    @Rule
    @JvmField
    public var avtivityRule = ActivityTestRule(ItemListActivity::class.java)

    @Test
    fun recycleViewTest() {
        var recyclerView : RecyclerView = avtivityRule.activity.findViewById(R.id.item_list)
        var itemCount = recyclerView.adapter?.itemCount
        if (itemCount!=null){
            Espresso.onView(withId(R.id.item_list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount))
        }

//        Espresso.onView((withId(R.id.item_list))).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                1,
//                click()
//            )
//        )

//        Espresso.onView(ViewMatchers.withId(R.id.item_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
//
//        val articleTitle : String = "The Worst"
//        Espresso.onView(withText(articleTitle)).check(matches(isDisplayed()))
    }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}