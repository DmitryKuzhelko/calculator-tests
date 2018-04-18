package com.example.dahaka.mycaculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.dahaka.mycaculator.Calculator
import com.example.dahaka.mycaculator.NULL
import com.example.dahaka.mycaculator.data.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalculatorViewModel @Inject constructor(
        private val dataSource: DataSource
) : ViewModel() {

    private val stringLiveData = MutableLiveData<String>()
    private val calculator = Calculator()
    private var numberFirst = ""
    private var numberSecond = ""
    private var action = ""
    private var result = ""

    fun historyList(): LiveData<List<History>> {
        return dataSource.loadHistory()
    }

    private fun saveToHistory(time: Long) {
        if (stringLiveData.value != null)
            dataSource.saveToHistory(time, stringLiveData.value!!)
    }

    fun showDisplay(): LiveData<String> {
        return stringLiveData
    }

    fun setNumber(number: String) {
        if (action == "=") {
            clearDisplay()
        }
        if (action == "") {
            numberFirst += number
        } else {
            numberSecond += number
        }
        if (stringLiveData.value == null) {
            stringLiveData.value = "" + number
        } else {
            stringLiveData.value = stringLiveData.value + number
        }
    }

    fun setOperator(operator: String) {
        if (numberFirst.isEmpty()) return
        when (action) {
            "" -> {
                action += operator
                stringLiveData.value = stringLiveData.value + operator
            }
            "=" -> clearDisplay()
            else -> return
        }
    }

    fun setResult(operator: String) {
        result = chooseOperation(action)
        if (action == "=" || numberSecond.isEmpty() || result == NULL) {
            return
        }
        action = "="
        if (stringLiveData.value == null) {
            stringLiveData.value = ("" + operator + result)
        } else {
            stringLiveData.value = (stringLiveData.value + operator + result)
        }
        saveToHistory(System.currentTimeMillis())
    }

    fun setHistoryValue(text: String) {
        stringLiveData.value = text
        action = "="
    }

    fun clearDisplay() {
        stringLiveData.value = ""
        numberFirst = ""
        numberSecond = ""
        action = ""
        result = ""
    }

    fun clearDb() {
        dataSource.clearDataFromDb()
    }

    fun removeLastSymbol() {
        if (stringLiveData.value == null || stringLiveData.value!!.contains('=')) {
            clearDisplay()
            return
        }
        if (stringLiveData.value != null && stringLiveData.value?.length != 0) {
            val lDLength = stringLiveData.value?.length ?: 0
            if (stringLiveData.value!![lDLength - 1].isDigit()) {
                if (numberSecond.isEmpty() && numberFirst.isNotEmpty()) {
                    numberFirst = numberFirst.substring(0, numberFirst.length - 1)
                } else if (numberSecond.isNotEmpty()) {
                    numberSecond = numberSecond.substring(0, numberSecond.length - 1)
                }
            } else {
                action = ""
            }
            stringLiveData.value = stringLiveData.value?.substring(0, lDLength - 1)
        }
    }

    private fun chooseOperation(operator: String): String {
        return when (operator) {
            "+" -> calculator.addition(numberFirst.toLong(), numberSecond.toLong()).toString()
            "-" -> calculator.subtraction(numberFirst.toLong(), numberSecond.toLong()).toString()
            "*" -> calculator.multiply(numberFirst.toLong(), numberSecond.toLong()).toString()
            "/" -> calculator.division(numberFirst.toLong(), numberSecond.toLong())
            else -> ""
        }
    }
}