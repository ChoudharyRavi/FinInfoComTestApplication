package com.example.fininfocomtestapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fininfocomtestapplication.model.NumberItemModel
import com.example.fininfocomtestapplication.utils.Utilities
import kotlinx.coroutines.launch
import kotlin.math.sqrt

class NumberViewModel : ViewModel() {

    private val numbers = (1..100).toList()
    private val _filteredNumbers = MutableLiveData<List<NumberItemModel>>()
    val filteredNumbers: LiveData<List<NumberItemModel>> get() = _filteredNumbers

    fun setPatterns(pattern: Utilities.AppPatterns) {
        viewModelScope.launch {
            val filtered = numbers.map { number ->
                NumberItemModel(
                    number,
                    changeBackground(number = number, appPatterns = pattern),
                    appPatterns = pattern
                )
            }
            _filteredNumbers.value = filtered
        }
    }

    private fun changeBackground(number: Int, appPatterns: Utilities.AppPatterns): Boolean {
        return when (appPatterns) {
            Utilities.AppPatterns.ODD_NUMBER -> number % 2 != 0
            Utilities.AppPatterns.EVEN_NUMBER -> number % 2 == 0
            Utilities.AppPatterns.PRIME_NUMBER -> detectPrimeNumber(number)
            Utilities.AppPatterns.FIBONACCI_SEQUENCE -> findFibonacciNo(number)
        }
    }

    private fun detectPrimeNumber(number: Int): Boolean {
        number.apply {
            if (this <= 1) return false
            if (this == 2) return true
            if (this % 2 == 0) return false
            for (i in 3..sqrt(toDouble()).toInt() step 2) {
                if (this % i == 0) return false
            }
            return true
        }
    }

    private fun findFibonacciNo(num: Int): Boolean {
        var num1 = 0
        var num2 = 1
        var num3 = 0
        while (num3 < num) {
            num3 = num1 + num2
            num1 = num2
            num2 = num3
        }
        return num3 == num
    }
}

