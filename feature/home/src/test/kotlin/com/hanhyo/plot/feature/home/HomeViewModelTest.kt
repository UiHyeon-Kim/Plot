package com.hanhyo.plot.feature.home

import com.hanhyo.plot.core.domain.usecase.GetGreetingUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `Refresh intent 처리 후 state에 메시지가 반영된다`() = runTest {
        val viewModel = HomeViewModel(GetGreetingUseCase())

        viewModel.handleIntent(HomeIntent.Refresh)

        assertEquals("Hello, Plot!", viewModel.state.value.message)
    }
}
