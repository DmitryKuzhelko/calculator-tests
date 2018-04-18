package dmitry.kuzhelko.calculator

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import dmitry.kuzhelko.calculator.ui.main.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule(MainActivity::class.java)

    private fun press(id: Int) {
        onView(withId(id)).perform(click())
    }

    private fun checkResult(desired: String) {
        onView(withId(R.id.display)).check(matches(withText(desired)))
    }

    @Test
    fun addDigits() {
        press(R.id.button1)
        press(R.id.button2)
        press(R.id.button3)
        press(R.id.button4)
        press(R.id.button5)
        press(R.id.button6)
        press(R.id.button7)
        press(R.id.button8)
        press(R.id.button9)
        press(R.id.button0)
        checkResult("1234567890")
    }

    @Test
    fun additionTest() {
        press(R.id.button1)
        press(R.id.buttonAdd)
        press(R.id.button3)
        press(R.id.buttonEql)
        checkResult("1+3=4")
    }

    @Test
    fun subtractionTest() {
        press(R.id.button5)
        press(R.id.buttonSub)
        press(R.id.button3)
        press(R.id.buttonEql)
        checkResult("5-3=2")
    }

    @Test
    fun multiplyTest() {
        press(R.id.button3)
        press(R.id.buttonMul)
        press(R.id.button2)
        press(R.id.buttonEql)
        checkResult("3*2=6")
    }

    @Test
    fun divisionTest() {
        press(R.id.button8)
        press(R.id.buttonDiv)
        press(R.id.button2)
        press(R.id.buttonEql)
        checkResult("8/2=4")
    }

    @Test
    fun divisionByZeroTest() {
        press(R.id.button8)
        press(R.id.buttonDiv)
        press(R.id.button2)
        press(R.id.buttonEql)
        checkResult("1/0=0")
    }
}