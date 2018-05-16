package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.math.BigInteger

@RestController
@RequestMapping("/api/v1/calculator")
class CalculatorService {
    @GetMapping("/add")
    fun add(vararg addable: BigDecimal): BigDecimal {
        var result = BigDecimal(BigInteger.ZERO)
        addable.forEach { input -> result = result.add(input) }
        return result
    }
}

