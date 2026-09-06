package com.hanhyo.plot.feature.home

import androidx.lifecycle.ViewModel
import com.hanhyo.plot.core.domain.usecase.GetGreetingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGreetingUseCase: GetGreetingUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Refresh -> _state.value = _state.value.copy(message = getGreetingUseCase())
        }
    }
}
