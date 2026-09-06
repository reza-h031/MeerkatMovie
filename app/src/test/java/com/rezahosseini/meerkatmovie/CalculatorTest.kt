package com.rezahosseini.meerkatmovie

import com.rezahosseini.meerkatmovie.learntest.Calculator
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setup(){
        calculator = Calculator()
    }
    @Test
    fun add_shouldReturnSum() {


        val resultAdd = calculator.add(2, 3)

        assertEquals(5, resultAdd)
    }
    @Test
    fun subtract_shouldReturnDifference() {


        val resultSubtract = calculator.subtract(2, 3)

        assertEquals(-1, resultSubtract)
    }
    @Test
    fun multiply_shouldReturnProduct() {


        val resultMultiply = calculator.multiply(2, 3)

        assertEquals(6, resultMultiply)
    }
    @Test
    fun divide_shouldReturnQuotient() {


        val resultDivide= calculator.divide(4, 2)

        assertEquals(2, resultDivide)
    }
}