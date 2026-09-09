package com.hanhyo.plot.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

private val Teal500 = Color(0xFF14B8A6)
private val Teal700 = Color(0xFF0F766E)
private val Teal50 = Color(0xFFF0FDFA)
private val Gray50 = Color(0xFFF9FAFB)
private val Gray100 = Color(0xFFF3F4F6)
private val Gray200 = Color(0xFFE5E7EB)
private val Gray300 = Color(0xFFD1D5DB)
private val Gray400 = Color(0xFF9CA3AF)
private val Gray500 = Color(0xFF6B7280)
private val Gray600 = Color(0xFF4B5563)
private val Gray700 = Color(0xFF374151)
private val Gray800 = Color(0xFF1F2937)
private val Gray900 = Color(0xFF111827)

@Immutable
data class PlotColors(
    val background: Color,
    val surface: Color,
    val surfaceSubtle: Color,
    val borderSubtle: Color,
    val border: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textMuted: Color,
    val textFaint: Color,
    val controlMuted: Color,
    val primary: Color,
    val primaryDark: Color,
    val primaryContainer: Color,
    val onPrimary: Color,
    val danger: Color,
    val categoryWork: Color,
    val categoryMeeting: Color,
    val categoryAppointment: Color,
    val categoryRoutine: Color,
    val scrim: Color,
)

internal val LightPlotColors = PlotColors(
    background = Color(0xFFF8FFFE),
    surface = Color.White,
    surfaceSubtle = Gray50,
    borderSubtle = Gray100,
    border = Gray200,
    textPrimary = Gray900,
    textSecondary = Gray800,
    textTertiary = Gray700,
    textMuted = Gray500,
    textFaint = Gray400,
    controlMuted = Gray300,
    primary = Teal500,
    primaryDark = Teal700,
    primaryContainer = Teal50,
    onPrimary = Color.White,
    danger = Color(0xFFDC2626),
    categoryWork = Color(0xFF6366F1),
    categoryMeeting = Color(0xFF475569),
    categoryAppointment = Color(0xFFB45309),
    categoryRoutine = Color(0xFF16A34A),
    scrim = Color(0x470A1628),
)

internal val MaterialLightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = LightPlotColors.primary,
    onPrimary = LightPlotColors.onPrimary,
    primaryContainer = LightPlotColors.primaryContainer,
    onPrimaryContainer = LightPlotColors.primaryDark,
    background = LightPlotColors.background,
    onBackground = LightPlotColors.textPrimary,
    surface = LightPlotColors.surface,
    onSurface = LightPlotColors.textPrimary,
    outline = LightPlotColors.border,
    error = LightPlotColors.danger,
)
