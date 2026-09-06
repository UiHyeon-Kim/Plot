package com.hanhyo.plot.feature.home

data class HomeState(val message: String = "")

sealed interface HomeIntent {
    data object Refresh : HomeIntent
}
