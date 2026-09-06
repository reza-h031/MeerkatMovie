package com.rezahosseini.meerkatmovie

import com.rezahosseini.meerkatmovie.learntest.Calculator
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun add_shouldReturnSum() {

        val calculator = Calculator()

        val resultAdd = calculator.add(2, 3)

        assertEquals(5, resultAdd)
    }
    @Test
    fun subtract_shouldReturnSum() {

        val calculator = Calculator()

        val resultSubtract = calculator.subtract(2, 3)

        assertEquals(-1, resultSubtract)
    }
    @Test
    fun multiply_shouldReturnSum() {

        val calculator = Calculator()

        val resultMultiply = calculator.multiply(2, 3)

        assertEquals(6, resultMultiply)
    }
    @Test
    fun divide_shouldReturnSum() {

        val calculator = Calculator()

        val resultDivide= calculator.divide(4, 2)

        assertEquals(2, resultDivide)
    }
}