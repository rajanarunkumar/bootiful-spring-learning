package com.example

import com.example.demo.AbstractTestHelper
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

class CalculatorServiceTest : AbstractTestHelper() {

    @Autowired
    lateinit var calculatorService: CalculatorService

    @Test
    fun add() {
        check(calculatorService.add(BigDecimal(1), BigDecimal(2), BigDecimal(3)) == BigDecimal(6)) { "Result must be 6" }
    }
}