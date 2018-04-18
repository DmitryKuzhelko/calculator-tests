package dmitry.kuzhelko.calculator

import dmitry.kuzhelko.calculator.ui.main.view.Calculator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculatorTest {

    private lateinit var calculator: Calculator

    @Before
    @Throws(Exception::class)
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    @Throws(Exception::class)
    fun addition() {
        assertEquals(3, calculator.addition("1", "2"))
        assertEquals(1, calculator.addition("1", "0"))
    }

    @Test
    @Throws(Exception::class)
    fun subtract() {
        assertEquals(1, calculator.subtract("7", "6"))
        assertEquals(-2, calculator.subtract("0", "2"))
    }

    @Test
    @Throws(Exception::class)
    fun multiply() {
        assertEquals(12, calculator.multiply("3", "4"))
        assertEquals(0, calculator.multiply("9", "0"))
    }

    @Test
    @Throws(Exception::class)
    fun divide() {
        assertEquals(2, calculator.divide("8", "4"))
        assertEquals(0, calculator.divide("5", "0"))
    }
}