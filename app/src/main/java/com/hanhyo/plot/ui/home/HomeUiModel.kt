package com.hanhyo.plot.ui.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeDayUiModel(
    val weekday: String,
    val day: Int,
    val hasSchedule: Boolean,
    val isSelected: Boolean,
)

enum class TimelineCategory {
    Focus,
    Work,
    Appointment,
}

@Immutable
data class TimelineBlockUiModel(
    val title: String,
    val time: String,
    val startMinute: Int,
    val durationMinutes: Int,
    val category: TimelineCategory,
    val isLocked: Boolean = false,
)

enum class TaskPriority {
    Urgent,
    Focus,
    None,
}

@Immutable
data class UnscheduledTaskUiModel(
    val title: String,
    val estimate: String,
    val priority: TaskPriority,
)

object HomePreviewData {
    val days = listOf(
        HomeDayUiModel("월", 21, hasSchedule = true, isSelected = false),
        HomeDayUiModel("화", 22, hasSchedule = false, isSelected = false),
        HomeDayUiModel("수", 23, hasSchedule = false, isSelected = false),
        HomeDayUiModel("목", 24, hasSchedule = true, isSelected = false),
        HomeDayUiModel("금", 25, hasSchedule = true, isSelected = false),
        HomeDayUiModel("토", 26, hasSchedule = false, isSelected = false),
        HomeDayUiModel("일", 27, hasSchedule = true, isSelected = true),
    )

    val timelineBlocks = listOf(
        TimelineBlockUiModel(
            title = "팀 미팅",
            time = "09:00 – 10:00",
            startMinute = 9 * 60,
            durationMinutes = 60,
            category = TimelineCategory.Focus,
            isLocked = true,
        ),
        TimelineBlockUiModel(
            title = "보고서 작성",
            time = "10:30 – 12:00",
            startMinute = 10 * 60 + 30,
            durationMinutes = 90,
            category = TimelineCategory.Work,
        ),
        TimelineBlockUiModel(
            title = "점심 약속",
            time = "13:00 – 14:00",
            startMinute = 13 * 60,
            durationMinutes = 60,
            category = TimelineCategory.Appointment,
        ),
    )

    val unscheduledTasks = listOf(
        UnscheduledTaskUiModel("분기 보고서 제출", "1h", TaskPriority.Urgent),
        UnscheduledTaskUiModel("약 복용", "5m", TaskPriority.Focus),
        UnscheduledTaskUiModel("팀 피드백 정리", "30m", TaskPriority.None),
    )
}
