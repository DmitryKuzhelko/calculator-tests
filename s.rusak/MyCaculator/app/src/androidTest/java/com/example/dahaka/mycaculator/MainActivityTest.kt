package com.example.dahaka.mycaculator

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.example.dahaka.mycaculator.activity.MainActivity
import android.support.test.rule.ActivityTestRule
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickButtonsTest() {
        onView(withText("1")).perform(click())
        onView(withText("2")).perform(click())
        onView(withText("3")).perform(click())
        onView(withText("4")).perform(click())
        onView(withText("5")).perform(click())
        onView(withText("6")).perform(click())
        onView(withText("7")).perform(click())
        onView(withText("8")).perform(click())
        onView(withText("9")).perform(click())
        onView(withText("0")).perform(click())
        onView(withText("+")).perform(click())
        onView(withId(R.id.display)).check(matches(ViewMatchers.withText("1234567890+")))
    }

    @Test
    fun clickButtonsTest2() {
        onView(withText("9")).perform(click())
        onView(withText("-")).perform(click())
        onView(withText("erase")).perform(click())
        onView(withText("*")).perform(click())
        onView(withText("3")).perform(click())
        onView(withText("=")).perform(click())
        onView(withId(R.id.display)).check(matches(ViewMatchers.withText("9*3=27")))
    }

    @Test
    fun clickButtonsTest3() {
        onView(withText("history")).perform(click())
        onView(withId(R.id.recycler)).check(matches(ViewMatchers.withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.display)).check(matches(ViewMatchers.withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.history)).check(matches(ViewMatchers.withResourceName("history")))
        onView(withText("back")).perform(click())
        onView(withId(R.id.recycler)).check(matches(ViewMatchers.withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.display)).check(matches(ViewMatchers.withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun clickButtonsTest4() {
        onView(withText("9")).perform(click())
        onView(withText("5")).perform(click())
        onView(withText("7")).perform(click())
        onView(withText("/")).perform(click())
        onView(withText("erase")).perform(longClick())  // longClick
        onView(withId(R.id.display)).check(matches(ViewMatchers.withText("")))
    }
}