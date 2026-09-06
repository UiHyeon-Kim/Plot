package com.hanhyo.plot.core.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test

class GetGreetingUseCaseTest {

    private val useCase = GetGreetingUseCase()

    @Test
    fun `invoke는 인사 메시지를 반환한다`() {
        assertEquals("Hello, Plot!", useCase())
    }
}
