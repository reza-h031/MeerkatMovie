package com.rezahosseini.meerkatmovie

import com.rezahosseini.meerkatmovie.learntest.Calculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
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
    fun add_shouldReturnSum2() {


        val resultAdd = calculator.add(5, 0)

        assertEquals(5, resultAdd)
    }
    @Test
    fun add_shouldReturnSum3() {


        val resultAdd = calculator.add(-2, 3)

        assertEquals(1, resultAdd)
    }
    @Test
    fun subtract_shouldReturnDifference() {


        val resultSubtract = calculator.subtract(5, 2)

        assertEquals(3, resultSubtract)
    }
    @Test
    fun subtract_shouldReturnDifference2() {


        val resultSubtract = calculator.subtract(5, 0)

        assertEquals(5, resultSubtract)
    }
    @Test
    fun subtract_shouldReturnDifference3() {


        val resultSubtract = calculator.subtract(2, 5)

        assertEquals(-3, resultSubtract)
    }
    @Test
    fun multiply_shouldReturnProduct() {


        val resultMultiply = calculator.multiply(2, 3)

        assertEquals(6, resultMultiply)
    }
    @Test
    fun multiply_shouldReturnProduct2() {


        val resultMultiply = calculator.multiply(5, 0)

        assertEquals(0, resultMultiply)
    }
    @Test
    fun multiply_shouldReturnProduct3() {


        val resultMultiply = calculator.multiply(-2, 3)

        assertEquals(-6, resultMultiply)
    }

    @Test
    fun divide_shouldReturnQuotient() {


        val resultDivide= calculator.divide(2, 6)

        assertEquals(0, resultDivide)
    }
    @Test
    fun divide_shouldReturnQuotient2() {


        val resultDivide= calculator.divide(5, 1)

        assertEquals(5, resultDivide)
    }
    @Test
    fun divide_shouldReturnQuotient3() {


        val resultDivide= calculator.divide(-6, 2)

        assertEquals(-3, resultDivide)
    }
    @Test
    fun add_shouldReturnPositiveNumber() {

        val result = calculator.add(2, 3)

        assertTrue(result > 0)
    }
    @Test
    fun divide_shouldReturnPositiveNumber(){
        val result=calculator.divide(4,2)
        assertFalse(result<0)
    }
    @Test
    fun multiply_shouldReturnIsNull(){
        val result=calculator.multiply(2,2)
        assertNotNull(result)
    }
    @Test
    fun calculator_shouldReturnToSame(){
        val resultMultiply=calculator
        val resultAdd=calculator
        assertSame(resultAdd,resultMultiply)
    }
}