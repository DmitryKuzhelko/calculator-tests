package com.example.dahaka.mycaculator

open class Calculator {

    fun addition(op1: Long, op2: Long) = op1 + op2

    fun subtraction(op1: Long, op2: Long) = op1 - op2

    fun multiply(op1: Long, op2: Long) = op1 * op2

    fun division(op1: Long, op2: Long): String {
        if (op2 > 0) {
            return (op1.toDouble() / op2.toDouble()).toString()
        }
        return NULL
    }
}

