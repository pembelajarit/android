package com.example.testing

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        val calc = Calculator()
        assertEquals(4, calc.add(2,2))
    }

    @Test
    fun substraction_isCorrect(){
        val calc = Calculator()
        assertEquals(2,calc.substract(5,3))
    }
}