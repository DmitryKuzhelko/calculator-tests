package com.example.dahaka.mycaculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun testResult() {
        assertTrue(calculator.addition(2, 3) == 5L)
        assertTrue(calculator.division(6, 2) == "3.0")
        assertTrue(calculator.division(6, 0) == NULL)
        assertTrue(calculator.multiply(3, 2) == 6L)
        assertTrue(calculator.subtraction(9, 7) == 2L)
        assertEquals(6, calculator.multiply(2, 3))
        assertEquals("9.0", calculator.division(18, 2))
    }
}