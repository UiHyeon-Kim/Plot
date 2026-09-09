package com.hanhyo.plot.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

internal enum class HomeIconType {
    Bell,
    Review,
    Clock,
    ChevronRight,
    Plus,
    Lock,
    List,
    Grip,
    Home,
    Checklist,
    Calendar,
    Matrix,
    Habit,
    Settings,
    Goal,
    Subscription,
}

@Composable
internal fun HomeIcon(
    type: HomeIconType,
    tint: Color,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val semanticsModifier = if (contentDescription == null) {
        modifier
    } else {
        modifier.semantics { this.contentDescription = contentDescription }
    }
    Canvas(semanticsModifier) {
        drawHomeIcon(type = type, tint = tint)
    }
}

private fun DrawScope.drawHomeIcon(type: HomeIconType, tint: Color) {
    val unit = size.minDimension / 24f
    val origin = Offset((size.width - 24f * unit) / 2f, (size.height - 24f * unit) / 2f)
    val stroke = Stroke(
        width = 1.9f * unit,
        cap = StrokeCap.Round,
        join = StrokeJoin.Round,
    )
    fun p(x: Float, y: Float) = Offset(origin.x + x * unit, origin.y + y * unit)
    fun line(x1: Float, y1: Float, x2: Float, y2: Float) =
        drawLine(tint, p(x1, y1), p(x2, y2), stroke.width, StrokeCap.Round)

    when (type) {
        HomeIconType.Bell -> {
            val path = Path().apply {
                moveTo(p(18f, 8f).x, p(18f, 8f).y)
                cubicTo(p(18f, 4.7f).x, p(18f, 4.7f).y, p(15.3f, 2f).x, p(15.3f, 2f).y, p(12f, 2f).x, p(12f, 2f).y)
                cubicTo(p(8.7f, 2f).x, p(8.7f, 2f).y, p(6f, 4.7f).x, p(6f, 4.7f).y, p(6f, 8f).x, p(6f, 8f).y)
                cubicTo(p(6f, 15f).x, p(6f, 15f).y, p(3f, 17f).x, p(3f, 17f).y, p(3f, 17f).x, p(3f, 17f).y)
                lineTo(p(21f, 17f).x, p(21f, 17f).y)
                cubicTo(p(21f, 17f).x, p(21f, 17f).y, p(18f, 15f).x, p(18f, 15f).y, p(18f, 8f).x, p(18f, 8f).y)
            }
            drawPath(path, tint, style = stroke)
            val clapper = Path().apply {
                moveTo(p(13.73f, 21f).x, p(13.73f, 21f).y)
                cubicTo(p(12.95f, 22.35f).x, p(12.95f, 22.35f).y, p(11.05f, 22.35f).x, p(11.05f, 22.35f).y, p(10.27f, 21f).x, p(10.27f, 21f).y)
            }
            drawPath(clapper, tint, style = stroke)
        }

        HomeIconType.Review -> {
            line(3f, 3f, 3f, 21f)
            line(3f, 21f, 21f, 21f)
            val graph = Path().apply {
                moveTo(p(7f, 14f).x, p(7f, 14f).y)
                lineTo(p(10f, 11f).x, p(10f, 11f).y)
                lineTo(p(13f, 13f).x, p(13f, 13f).y)
                lineTo(p(17f, 8f).x, p(17f, 8f).y)
            }
            drawPath(graph, tint, style = stroke)
        }

        HomeIconType.Clock -> {
            drawCircle(tint, 8f * unit, p(12f, 13f), style = stroke)
            line(12f, 9f, 12f, 13f)
            line(12f, 13f, 14.5f, 15f)
            line(9f, 2f, 15f, 2f)
        }

        HomeIconType.ChevronRight -> {
            line(9f, 6f, 15f, 12f)
            line(15f, 12f, 9f, 18f)
        }

        HomeIconType.Plus -> {
            line(12f, 5f, 12f, 19f)
            line(5f, 12f, 19f, 12f)
        }

        HomeIconType.Lock -> {
            drawRoundRect(
                color = tint,
                topLeft = p(6f, 10f),
                size = Size(12f * unit, 10f * unit),
                cornerRadius = CornerRadius(1.5f * unit),
                style = stroke,
            )
            val shackle = Path().apply {
                moveTo(p(8.5f, 10f).x, p(8.5f, 10f).y)
                lineTo(p(8.5f, 7f).x, p(8.5f, 7f).y)
                cubicTo(p(8.5f, 2.8f).x, p(8.5f, 2.8f).y, p(15.5f, 2.8f).x, p(15.5f, 2.8f).y, p(15.5f, 7f).x, p(15.5f, 7f).y)
                lineTo(p(15.5f, 10f).x, p(15.5f, 10f).y)
            }
            drawPath(shackle, tint, style = stroke)
        }

        HomeIconType.List -> {
            line(3f, 6f, 21f, 6f)
            line(3f, 12f, 21f, 12f)
            line(3f, 18f, 21f, 18f)
        }

        HomeIconType.Grip -> {
            listOf(6f, 12f, 18f).forEach { y ->
                drawCircle(tint, 1.4f * unit, p(9f, y))
                drawCircle(tint, 1.4f * unit, p(15f, y))
            }
        }

        HomeIconType.Home -> {
            val roof = Path().apply {
                moveTo(p(3f, 11f).x, p(3f, 11f).y)
                lineTo(p(12f, 3f).x, p(12f, 3f).y)
                lineTo(p(21f, 11f).x, p(21f, 11f).y)
                lineTo(p(21f, 20f).x, p(21f, 20f).y)
                quadraticTo(p(21f, 21f).x, p(21f, 21f).y, p(20f, 21f).x, p(20f, 21f).y)
                lineTo(p(4f, 21f).x, p(4f, 21f).y)
                quadraticTo(p(3f, 21f).x, p(3f, 21f).y, p(3f, 20f).x, p(3f, 20f).y)
                close()
            }
            drawPath(roof, tint, style = stroke)
            line(9f, 21f, 9f, 13f)
            line(9f, 13f, 15f, 13f)
            line(15f, 13f, 15f, 21f)
        }

        HomeIconType.Checklist -> {
            listOf(6f, 12f, 18f).forEach { y -> line(9f, y, 20f, y) }
            listOf(5f, 11f, 17f).forEach { y ->
                line(4f, y, 5.3f, y + 1.3f)
                line(5.3f, y + 1.3f, 7.3f, y - 1.1f)
            }
        }

        HomeIconType.Calendar -> {
            drawRoundRect(tint, p(3f, 4f), Size(18f * unit, 18f * unit), CornerRadius(2.5f * unit), style = stroke)
            line(3f, 10f, 21f, 10f)
            line(8f, 2f, 8f, 6f)
            line(16f, 2f, 16f, 6f)
            listOf(8f, 12f, 16f).forEach { x -> line(x - 1f, 15f, x + 1f, 15f) }
        }

        HomeIconType.Matrix -> {
            drawRoundRect(tint, p(3f, 3f), Size(18f * unit, 18f * unit), CornerRadius(2f * unit), style = stroke)
            line(12f, 3f, 12f, 21f)
            line(3f, 12f, 21f, 12f)
            drawCircle(tint, unit, p(7.5f, 7.5f))
            drawCircle(tint, unit, p(16.5f, 7.5f))
        }

        HomeIconType.Habit -> {
            val top = Path().apply {
                moveTo(p(17f, 2f).x, p(17f, 2f).y)
                lineTo(p(21f, 6f).x, p(21f, 6f).y)
                lineTo(p(17f, 10f).x, p(17f, 10f).y)
                moveTo(p(3f, 11f).x, p(3f, 11f).y)
                lineTo(p(3f, 9f).x, p(3f, 9f).y)
                cubicTo(p(3f, 6.8f).x, p(3f, 6.8f).y, p(4.8f, 5f).x, p(4.8f, 5f).y, p(7f, 5f).x, p(7f, 5f).y)
                lineTo(p(21f, 5f).x, p(21f, 5f).y)
            }
            drawPath(top, tint, style = stroke)
            val bottom = Path().apply {
                moveTo(p(7f, 22f).x, p(7f, 22f).y)
                lineTo(p(3f, 18f).x, p(3f, 18f).y)
                lineTo(p(7f, 14f).x, p(7f, 14f).y)
                moveTo(p(21f, 13f).x, p(21f, 13f).y)
                lineTo(p(21f, 15f).x, p(21f, 15f).y)
                cubicTo(p(21f, 17.2f).x, p(21f, 17.2f).y, p(19.2f, 19f).x, p(19.2f, 19f).y, p(17f, 19f).x, p(17f, 19f).y)
                lineTo(p(3f, 19f).x, p(3f, 19f).y)
            }
            drawPath(bottom, tint, style = stroke)
        }

        HomeIconType.Settings -> {
            listOf(6f, 12f, 18f).forEach { y -> line(4f, y, 20f, y) }
            drawCircle(tint, 2.5f * unit, p(8f, 6f), style = Stroke(width = stroke.width))
            drawCircle(tint, 2.5f * unit, p(16f, 12f), style = Stroke(width = stroke.width))
            drawCircle(tint, 2.5f * unit, p(10f, 18f), style = Stroke(width = stroke.width))
        }

        HomeIconType.Goal -> {
            val star = Path().apply {
                moveTo(p(12f, 2f).x, p(12f, 2f).y)
                lineTo(p(14.4f, 9.4f).x, p(14.4f, 9.4f).y)
                lineTo(p(22f, 9.4f).x, p(22f, 9.4f).y)
                lineTo(p(16f, 13.9f).x, p(16f, 13.9f).y)
                lineTo(p(18.3f, 21f).x, p(18.3f, 21f).y)
                lineTo(p(12f, 16.4f).x, p(12f, 16.4f).y)
                lineTo(p(5.7f, 21f).x, p(5.7f, 21f).y)
                lineTo(p(8f, 13.9f).x, p(8f, 13.9f).y)
                lineTo(p(2f, 9.4f).x, p(2f, 9.4f).y)
                lineTo(p(9.6f, 9.4f).x, p(9.6f, 9.4f).y)
                close()
            }
            drawPath(star, tint, style = stroke)
        }

        HomeIconType.Subscription -> {
            val shield = Path().apply {
                moveTo(p(12f, 2f).x, p(12f, 2f).y)
                lineTo(p(20f, 6f).x, p(20f, 6f).y)
                lineTo(p(20f, 12f).x, p(20f, 12f).y)
                cubicTo(p(20f, 17f).x, p(20f, 17f).y, p(16.5f, 20f).x, p(16.5f, 20f).y, p(12f, 22f).x, p(12f, 22f).y)
                cubicTo(p(7.5f, 20f).x, p(7.5f, 20f).y, p(4f, 17f).x, p(4f, 17f).y, p(4f, 12f).x, p(4f, 12f).y)
                lineTo(p(4f, 6f).x, p(4f, 6f).y)
                close()
            }
            drawPath(shield, tint, style = stroke)
            line(9f, 12f, 11f, 14f)
            line(11f, 14f, 15f, 10f)
        }
    }
}
