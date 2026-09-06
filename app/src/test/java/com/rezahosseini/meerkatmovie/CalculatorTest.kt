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
    fun multiplyAndAdd_shouldReturnToSame(){
        val resultMultiply=calculator.multiply(2,2)
        val resultAdd=calculator.add(2,2)
        assertSame(resultAdd,resultMultiply)
    }
}