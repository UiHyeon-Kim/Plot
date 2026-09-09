package com.hanhyo.plot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalPlotColors = staticCompositionLocalOf { LightPlotColors }
private val LocalPlotTypography = staticCompositionLocalOf { PlotType }

object PlotTheme {
    val colors: PlotColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPlotColors.current

    val typography: PlotTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalPlotTypography.current
}

@Composable
fun PlotTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalPlotColors provides LightPlotColors,
        LocalPlotTypography provides PlotType,
    ) {
        MaterialTheme(
            colorScheme = MaterialLightColorScheme,
            typography = MaterialTypography,
            content = content,
        )
    }
}
