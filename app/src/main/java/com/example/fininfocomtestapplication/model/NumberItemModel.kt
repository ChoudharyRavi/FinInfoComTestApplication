package com.example.fininfocomtestapplication.model

import com.example.fininfocomtestapplication.utils.Utilities

data class NumberItemModel(
    val numberItem: Int,
    val isNumberSelected: Boolean,
    val appPatterns: Utilities.AppPatterns
)
