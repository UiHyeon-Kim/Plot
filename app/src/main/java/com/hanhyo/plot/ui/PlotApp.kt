package com.hanhyo.plot.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanhyo.plot.ui.home.HomeIcon
import com.hanhyo.plot.ui.home.HomeIconType
import com.hanhyo.plot.ui.home.HomeScreen
import com.hanhyo.plot.ui.home.ProfileMenuOverlay
import com.hanhyo.plot.ui.theme.PlotSize
import com.hanhyo.plot.ui.theme.PlotTheme

private data class BottomDestination(
    val label: String,
    val icon: HomeIconType,
)

private val destinations = listOf(
    BottomDestination("홈", HomeIconType.Home),
    BottomDestination("리스트", HomeIconType.Checklist),
    BottomDestination("캘린더", HomeIconType.Calendar),
    BottomDestination("매트릭스", HomeIconType.Matrix),
    BottomDestination("습관", HomeIconType.Habit),
)

@Composable
fun PlotApp(modifier: Modifier = Modifier) {
    val navigationBarInset = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    var isProfileMenuOpen by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PlotTheme.colors.background),
    ) {
        HomeScreen(
            isProfileMenuOpen = isProfileMenuOpen,
            onToggleProfileMenu = { isProfileMenuOpen = !isProfileMenuOpen },
            contentBottomPadding = PlotSize.BottomNavigation + navigationBarInset,
        )
        PlotFab(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 18.dp,
                    bottom = PlotSize.BottomNavigation + navigationBarInset + 12.dp,
                ),
        )
        PlotBottomNavigation(
            selectedLabel = "홈",
            onDestinationSelected = {},
            modifier = Modifier.align(Alignment.BottomCenter),
        )
        if (isProfileMenuOpen) {
            ProfileMenuOverlay(
                onDismiss = { isProfileMenuOpen = false },
                onOpenDestination = { isProfileMenuOpen = false },
            )
        }
    }
}

@Composable
private fun PlotFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(17.dp)
    Box(
        modifier = modifier
            .size(54.dp)
            .shadow(
                elevation = 12.dp,
                shape = shape,
                ambientColor = PlotTheme.colors.primary.copy(alpha = 0.28f),
                spotColor = PlotTheme.colors.primary.copy(alpha = 0.5f),
            )
            .clip(shape)
            .background(PlotTheme.colors.primary)
            .clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        HomeIcon(
            type = HomeIconType.Plus,
            tint = PlotTheme.colors.onPrimary,
            contentDescription = "새 할 일",
            modifier = Modifier.size(21.dp),
        )
    }
}

@Composable
private fun PlotBottomNavigation(
    selectedLabel: String,
    onDestinationSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = PlotTheme.colors.borderSubtle
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(PlotTheme.colors.surface.copy(alpha = 0.97f))
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                )
            }
            .navigationBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(PlotSize.BottomNavigation)
                .padding(horizontal = 8.dp)
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            destinations.forEach { destination ->
                BottomNavigationItem(
                    destination = destination,
                    isSelected = destination.label == selectedLabel,
                    onClick = { onDestinationSelected(destination.label) },
                )
            }
        }
    }
}

@Composable
private fun RowScope.BottomNavigationItem(
    destination: BottomDestination,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = if (isSelected) PlotTheme.colors.primary else PlotTheme.colors.textFaint
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.Tab,
            )
            .testTag("plot-nav-${destination.label}")
            .padding(top = 7.dp, bottom = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
    ) {
        HomeIcon(
            type = destination.icon,
            tint = contentColor,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
        )
        androidx.compose.material3.Text(
            text = destination.label,
            style = if (isSelected) PlotTheme.typography.captionStrong else PlotTheme.typography.caption,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

@Preview(name = "Plot app", showBackground = true, widthDp = 394, heightDp = 856)
@Composable
private fun PlotAppPreview() {
    PlotTheme {
        PlotApp()
    }
}
