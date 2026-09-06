package com.hanhyo.plot.core.domain.usecase

import javax.inject.Inject

class GetGreetingUseCase @Inject constructor() {
    operator fun invoke(target: String = "Plot"): String = "Hello, $target!"
}
