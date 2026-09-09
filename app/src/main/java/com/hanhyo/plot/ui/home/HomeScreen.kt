package com.hanhyo.plot.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hanhyo.plot.ui.theme.PlotRadius
import com.hanhyo.plot.ui.theme.PlotSize
import com.hanhyo.plot.ui.theme.PlotTheme

@Composable
fun HomeScreen(
    isProfileMenuOpen: Boolean,
    onToggleProfileMenu: () -> Unit,
    contentBottomPadding: Dp,
    modifier: Modifier = Modifier,
    onOpenBudget: () -> Unit = {},
    onOpenNotifications: () -> Unit = {},
    onOpenWeeklyReview: () -> Unit = {},
    onOpenTimeline: () -> Unit = {},
) {
    HomeContent(
        days = HomePreviewData.days,
        timelineBlocks = HomePreviewData.timelineBlocks,
        unscheduledTasks = HomePreviewData.unscheduledTasks,
        isProfileMenuOpen = isProfileMenuOpen,
        contentBottomPadding = contentBottomPadding,
        onOpenBudget = onOpenBudget,
        onOpenNotifications = onOpenNotifications,
        onOpenWeeklyReview = onOpenWeeklyReview,
        onOpenTimeline = onOpenTimeline,
        onToggleProfileMenu = onToggleProfileMenu,
        modifier = modifier,
    )
}

@Composable
fun HomeContent(
    days: List<HomeDayUiModel>,
    timelineBlocks: List<TimelineBlockUiModel>,
    unscheduledTasks: List<UnscheduledTaskUiModel>,
    isProfileMenuOpen: Boolean,
    contentBottomPadding: Dp,
    onOpenBudget: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenWeeklyReview: () -> Unit,
    onOpenTimeline: () -> Unit,
    onToggleProfileMenu: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PlotTheme.colors.background),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader(
                onOpenNotifications = onOpenNotifications,
                onToggleProfileMenu = onToggleProfileMenu,
                isProfileMenuOpen = isProfileMenuOpen,
            )
            WeekStrip(days = days)
            WeeklyReviewBanner(onClick = onOpenWeeklyReview)
            TimeBudgetBar(onClick = onOpenBudget)
            TimelineTitle(onClick = onOpenTimeline)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
            ) {
                DayTimeline(blocks = timelineBlocks)
                UnscheduledTaskDrawer(tasks = unscheduledTasks)
                Spacer(modifier = Modifier.height(16.dp + contentBottomPadding))
            }
        }
    }
}

@Composable
private fun HomeHeader(
    onOpenNotifications: () -> Unit,
    onToggleProfileMenu: () -> Unit,
    isProfileMenuOpen: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(56.dp)
            .padding(start = 18.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            androidx.compose.material3.Text(
                text = "SUN, APR 27",
                style = PlotTheme.typography.captionStrong,
                color = PlotTheme.colors.textFaint,
            )
            androidx.compose.material3.Text(
                text = "오늘",
                style = PlotTheme.typography.title,
                color = PlotTheme.colors.textPrimary,
                modifier = Modifier.semantics { heading() },
            )
        }
        Box(
            modifier = Modifier
                .size(PlotSize.MinimumTouchTarget)
                .clip(RoundedCornerShape(10.dp))
                .clickable(role = Role.Button, onClick = onOpenNotifications),
            contentAlignment = Alignment.Center,
        ) {
            HomeIcon(
                type = HomeIconType.Bell,
                tint = PlotTheme.colors.textFaint,
                contentDescription = "알림",
                modifier = Modifier.size(18.dp),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-6).dp, y = 6.dp)
                    .size(7.dp)
                    .background(PlotTheme.colors.danger, CircleShape)
                    .border(1.5.dp, PlotTheme.colors.surface, CircleShape),
            )
        }
        AvatarButton(
            isActive = isProfileMenuOpen,
            size = 44,
            avatarSize = 34,
            contentDescription = "프로필",
            onClick = onToggleProfileMenu,
        )
    }
}

@Composable
private fun AvatarButton(
    isActive: Boolean,
    size: Int,
    avatarSize: Int,
    contentDescription: String?,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .clickable(role = Role.Button, onClick = onClick)
            .then(
                if (contentDescription == null) {
                    Modifier
                } else {
                    Modifier.semantics { this.contentDescription = contentDescription }
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        val ringModifier = if (isActive) {
            Modifier
                .size((avatarSize + 6).dp)
                .border(2.dp, PlotTheme.colors.primaryContainer, CircleShape)
                .padding(2.dp)
                .border(2.dp, PlotTheme.colors.primary, CircleShape)
                .padding(2.dp)
        } else {
            Modifier.size(avatarSize.dp)
        }
        Box(
            modifier = ringModifier
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        listOf(PlotTheme.colors.primary, PlotTheme.colors.primaryDark),
                    ),
                ),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.material3.Text(
                text = "김",
                style = PlotTheme.typography.labelStrong,
                color = PlotTheme.colors.onPrimary,
            )
        }
    }
}

@Composable
private fun WeekStrip(days: List<HomeDayUiModel>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, top = 8.dp, end = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        days.forEach { day ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(role = Role.Button, onClick = {})
                    .padding(vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                androidx.compose.material3.Text(
                    text = day.weekday,
                    style = PlotTheme.typography.caption,
                    color = PlotTheme.colors.textFaint,
                )
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .then(
                            if (day.isSelected) {
                                Modifier.background(PlotTheme.colors.primary, CircleShape)
                            } else {
                                Modifier
                            },
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    androidx.compose.material3.Text(
                    text = day.day.toString(),
                    style = PlotTheme.typography.heading,
                        color = if (day.isSelected) {
                            PlotTheme.colors.onPrimary
                        } else {
                            PlotTheme.colors.textMuted
                        },
                    )
                }
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(
                            when {
                                day.isSelected -> PlotTheme.colors.primary.copy(alpha = 0.45f)
                                day.hasSchedule -> PlotTheme.colors.primary
                                else -> PlotTheme.colors.primary.copy(alpha = 0f)
                            },
                            CircleShape,
                        ),
                )
            }
        }
    }
}

@Composable
private fun WeeklyReviewBanner(onClick: () -> Unit) {
    val shape = RoundedCornerShape(14.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 12.dp, end = 16.dp)
            .shadow(
                elevation = 8.dp,
                shape = shape,
                ambientColor = PlotTheme.colors.primaryDark.copy(alpha = 0.24f),
                spotColor = PlotTheme.colors.primaryDark.copy(alpha = 0.32f),
            )
            .clip(shape)
            .background(
                Brush.horizontalGradient(
                    listOf(PlotTheme.colors.primaryDark, PlotTheme.colors.primary),
                ),
            )
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(PlotTheme.colors.onPrimary.copy(alpha = 0.18f), RoundedCornerShape(11.dp)),
            contentAlignment = Alignment.Center,
        ) {
            HomeIcon(
                type = HomeIconType.Review,
                tint = PlotTheme.colors.onPrimary,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            androidx.compose.material3.Text(
                text = "이번 주 리뷰가 준비됐어요",
                style = PlotTheme.typography.bodyStrong,
                color = PlotTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            androidx.compose.material3.Text(
                text = "완료율 73% · 집중 14h 30m",
                style = PlotTheme.typography.label,
                color = PlotTheme.colors.onPrimary.copy(alpha = 0.85f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        HomeIcon(
            type = HomeIconType.ChevronRight,
            tint = PlotTheme.colors.onPrimary.copy(alpha = 0.72f),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )
    }
}

@Composable
private fun TimeBudgetBar(onClick: () -> Unit) {
    val shape = RoundedCornerShape(PlotRadius.Medium)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 14.dp, end = 16.dp)
            .clip(shape)
            .background(PlotTheme.colors.surface)
            .border(1.dp, PlotTheme.colors.borderSubtle, shape)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 13.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(PlotTheme.colors.primaryContainer, RoundedCornerShape(9.dp)),
            contentAlignment = Alignment.Center,
        ) {
            HomeIcon(
                type = HomeIconType.Clock,
                tint = PlotTheme.colors.primaryDark,
                contentDescription = null,
                modifier = Modifier.size(17.dp),
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                androidx.compose.material3.Text(
                    text = "오늘 시간 예산",
                    style = PlotTheme.typography.labelStrong,
                    color = PlotTheme.colors.textTertiary,
                    modifier = Modifier.weight(1f),
                )
                androidx.compose.material3.Text(
                    text = "3h 20m / 8h",
                    style = PlotTheme.typography.mono,
                    color = PlotTheme.colors.textMuted,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(PlotTheme.colors.borderSubtle),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(42f)
                        .background(PlotTheme.colors.primary),
                )
                Spacer(modifier = Modifier.weight(58f))
            }
        }
        HomeIcon(
            type = HomeIconType.ChevronRight,
            tint = PlotTheme.colors.border,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
    }
}

@Composable
private fun TimelineTitle(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 18.dp, end = 10.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        androidx.compose.material3.Text(
            text = "타임라인",
            style = PlotTheme.typography.bodyStrong,
            color = PlotTheme.colors.textSecondary,
            modifier = Modifier
                .weight(1f)
                .semantics { heading() },
        )
        Box(
            modifier = Modifier
                .height(PlotSize.MinimumTouchTarget)
                .clip(RoundedCornerShape(7.dp))
                .clickable(role = Role.Button, onClick = onClick)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.material3.Text(
                text = "전체 보기",
                style = PlotTheme.typography.captionStrong,
                color = PlotTheme.colors.primaryDark,
            )
        }
    }
}

@Composable
private fun DayTimeline(blocks: List<TimelineBlockUiModel>) {
    val hourHeight = 54.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(hourHeight * 6 + 6.dp),
    ) {
        (8..14).forEach { hour ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)
                    .offset(y = hourHeight * (hour - 8) + 6.dp),
                verticalAlignment = Alignment.Top,
            ) {
                androidx.compose.material3.Text(
                    text = "${if (hour > 12) hour - 12 else hour} ${if (hour < 12) "AM" else "PM"}",
                    style = PlotTheme.typography.mono,
                    color = PlotTheme.colors.controlMuted,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(42.dp)
                        .offset(y = (-6).dp)
                        .padding(end = 8.dp),
                )
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(PlotTheme.colors.borderSubtle),
                )
            }
        }

        blocks.forEach { block ->
            TimelineBlock(
                block = block,
                top = hourHeight * ((block.startMinute - 8 * 60) / 60f) + 6.dp,
                height = hourHeight * (block.durationMinutes / 60f),
            )
        }
        EmptyTimelineSlot(
            top = hourHeight * 4 + 6.dp,
            height = 38.dp,
        )
        CurrentTimeIndicator(top = 125.dp)
    }
}

@Composable
private fun TimelineBlock(
    block: TimelineBlockUiModel,
    top: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp,
) {
    val color = when (block.category) {
        TimelineCategory.Focus -> PlotTheme.colors.primary
        TimelineCategory.Work -> PlotTheme.colors.categoryWork
        TimelineCategory.Appointment -> PlotTheme.colors.categoryAppointment
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp, end = 18.dp)
            .offset(y = top)
            .height(height)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .clickable(role = Role.Button, onClick = {})
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) {
        Column {
            androidx.compose.material3.Text(
                text = block.title,
                style = PlotTheme.typography.labelStrong,
                color = PlotTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (height >= 44.dp) {
                androidx.compose.material3.Text(
                    text = block.time,
                    style = PlotTheme.typography.mono,
                    color = PlotTheme.colors.onPrimary.copy(alpha = 0.85f),
                    maxLines = 1,
                )
            }
        }
        if (block.isLocked) {
            HomeIcon(
                type = HomeIconType.Lock,
                tint = PlotTheme.colors.onPrimary.copy(alpha = 0.85f),
                contentDescription = "고정된 일정",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(13.dp),
            )
        }
    }
}

@Composable
private fun EmptyTimelineSlot(
    top: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp,
) {
    val shape = RoundedCornerShape(10.dp)
    val borderColor = PlotTheme.colors.border
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 60.dp, end = 18.dp)
            .offset(y = top)
            .height(height)
            .clip(shape)
            .drawBehind {
                drawRoundRect(
                    color = borderColor,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(10.dp.toPx()),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = 1.5.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(5.dp.toPx(), 4.dp.toPx()),
                        ),
                    ),
                )
            }
            .clickable(role = Role.Button, onClick = {})
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        HomeIcon(
            type = HomeIconType.Plus,
            tint = PlotTheme.colors.textFaint,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
        )
        androidx.compose.material3.Text(
            text = "할 일 배치",
            style = PlotTheme.typography.label,
            color = PlotTheme.colors.textFaint,
        )
    }
}

@Composable
private fun CurrentTimeIndicator(top: androidx.compose.ui.unit.Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 42.dp)
            .offset(y = top),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(PlotTheme.colors.danger, CircleShape)
                .border(2.dp, PlotTheme.colors.danger.copy(alpha = 0.28f), CircleShape),
        )
        Box(
            modifier = Modifier
                .height(1.5.dp)
                .weight(1f)
                .background(PlotTheme.colors.danger),
        )
    }
}

@Composable
private fun UnscheduledTaskDrawer(tasks: List<UnscheduledTaskUiModel>) {
    val shape = RoundedCornerShape(14.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 6.dp, end = 16.dp)
            .background(PlotTheme.colors.surfaceSubtle, shape)
            .border(1.dp, PlotTheme.colors.borderSubtle, shape)
            .padding(horizontal = 4.dp, vertical = 6.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 11.dp, end = 11.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HomeIcon(
                type = HomeIconType.List,
                tint = PlotTheme.colors.textMuted,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
            )
            Spacer(modifier = Modifier.width(7.dp))
            androidx.compose.material3.Text(
                text = "미배치 할 일",
                style = PlotTheme.typography.labelStrong,
                color = PlotTheme.colors.textTertiary,
            )
            Spacer(modifier = Modifier.width(6.dp))
            androidx.compose.material3.Text(
                text = tasks.size.toString(),
                style = PlotTheme.typography.mono,
                color = PlotTheme.colors.textFaint,
                modifier = Modifier.weight(1f),
            )
            androidx.compose.material3.Text(
                text = "끌어서 배치",
                style = PlotTheme.typography.caption,
                color = PlotTheme.colors.textFaint,
            )
        }
        tasks.forEach { task ->
            UnscheduledTaskRow(task = task)
        }
    }
}

@Composable
private fun UnscheduledTaskRow(task: UnscheduledTaskUiModel) {
    val shape = RoundedCornerShape(10.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.5.dp)
            .clip(shape)
            .background(PlotTheme.colors.surface)
            .border(1.dp, PlotTheme.colors.borderSubtle, shape)
            .clickable(role = Role.Button, onClick = {})
            .padding(horizontal = 11.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .border(2.dp, PlotTheme.colors.controlMuted, CircleShape),
        )
        Box(
            modifier = Modifier
                .size(7.dp)
                .background(
                    when (task.priority) {
                        TaskPriority.Urgent -> PlotTheme.colors.danger
                        TaskPriority.Focus -> PlotTheme.colors.primary
                        TaskPriority.None -> PlotTheme.colors.controlMuted
                    },
                    CircleShape,
                ),
        )
        androidx.compose.material3.Text(
            text = task.title,
            style = PlotTheme.typography.body,
            color = PlotTheme.colors.textSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        androidx.compose.material3.Text(
            text = task.estimate,
            style = PlotTheme.typography.mono,
            color = PlotTheme.colors.textFaint,
        )
        HomeIcon(
            type = HomeIconType.Grip,
            tint = PlotTheme.colors.controlMuted,
            contentDescription = "${task.title} 끌기",
            modifier = Modifier.size(13.dp),
        )
    }
}

@Composable
internal fun ProfileMenuOverlay(
    onDismiss: () -> Unit,
    onOpenDestination: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PlotTheme.colors.scrim)
                .clickable(role = Role.Button, onClick = onDismiss),
        )
        ProfileMenu(
            onOpenDestination = onOpenDestination,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 96.dp, end = 16.dp),
        )
    }
}

@Composable
private fun ProfileMenu(
    onOpenDestination: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(16.dp)
    Column(
        modifier = modifier
            .width(236.dp)
            .shadow(20.dp, shape)
            .background(PlotTheme.colors.surface, shape)
            .border(1.dp, PlotTheme.colors.borderSubtle, shape)
            .clip(shape),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(11.dp),
        ) {
            AvatarButton(
                isActive = false,
                size = 42,
                avatarSize = 42,
                contentDescription = null,
                onClick = {},
            )
            Column {
                androidx.compose.material3.Text(
                    text = "김의현",
                    style = PlotTheme.typography.heading,
                    color = PlotTheme.colors.textPrimary,
                )
                androidx.compose.material3.Text(
                    text = "Pro 플랜 이용 중",
                    style = PlotTheme.typography.captionStrong,
                    color = PlotTheme.colors.primaryDark,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(PlotTheme.colors.borderSubtle),
        )
        Column(modifier = Modifier.padding(6.dp)) {
            ProfileMenuRow("주간 리뷰", HomeIconType.Review, true, onOpenDestination)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .height(1.dp)
                    .background(PlotTheme.colors.borderSubtle),
            )
            ProfileMenuRow("설정", HomeIconType.Settings, false, onOpenDestination)
            ProfileMenuRow("목표 · 만다르트", HomeIconType.Goal, false, onOpenDestination)
            ProfileMenuRow("구독 관리", HomeIconType.Subscription, false, onOpenDestination)
        }
    }
}

@Composable
private fun ProfileMenuRow(
    label: String,
    icon: HomeIconType,
    emphasized: Boolean,
    onOpenDestination: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(9.dp))
            .clickable(role = Role.Button) { onOpenDestination(label) }
            .padding(horizontal = 11.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        val foreground = if (emphasized) PlotTheme.colors.primary else PlotTheme.colors.textFaint
        HomeIcon(
            type = icon,
            tint = foreground,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
        androidx.compose.material3.Text(
            text = label,
            style = if (emphasized) PlotTheme.typography.bodyStrong else PlotTheme.typography.body,
            color = if (emphasized) PlotTheme.colors.primaryDark else PlotTheme.colors.textMuted,
            modifier = Modifier.weight(1f),
        )
        HomeIcon(
            type = HomeIconType.ChevronRight,
            tint = PlotTheme.colors.border,
            contentDescription = null,
            modifier = Modifier.size(15.dp),
        )
    }
}

@Preview(name = "Home - default", showBackground = true, widthDp = 394, heightDp = 856)
@Composable
private fun HomeContentPreview() {
    PlotTheme {
        HomeContent(
            days = HomePreviewData.days,
            timelineBlocks = HomePreviewData.timelineBlocks,
            unscheduledTasks = HomePreviewData.unscheduledTasks,
            isProfileMenuOpen = false,
            contentBottomPadding = 0.dp,
            onOpenBudget = {},
            onOpenNotifications = {},
            onOpenWeeklyReview = {},
            onOpenTimeline = {},
            onToggleProfileMenu = {},
        )
    }
}

@Preview(name = "Home - profile menu", showBackground = true, widthDp = 394, heightDp = 856)
@Composable
private fun HomeProfileMenuPreview() {
    PlotTheme {
        Box {
            HomeContent(
                days = HomePreviewData.days,
                timelineBlocks = HomePreviewData.timelineBlocks,
                unscheduledTasks = HomePreviewData.unscheduledTasks,
                isProfileMenuOpen = true,
                contentBottomPadding = 0.dp,
                onOpenBudget = {},
                onOpenNotifications = {},
                onOpenWeeklyReview = {},
                onOpenTimeline = {},
                onToggleProfileMenu = {},
            )
            ProfileMenuOverlay(onDismiss = {}, onOpenDestination = {})
        }
    }
}
