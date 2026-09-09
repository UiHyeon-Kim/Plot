package com.hanhyo.plot.ui.home

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomePreviewDataTest {
    @Test
    fun canonicalFixture_matchesHomeV3Reference() {
        assertEquals(7, HomePreviewData.days.size)
        assertEquals(27, HomePreviewData.days.single { it.isSelected }.day)
        assertEquals(3, HomePreviewData.timelineBlocks.size)
        assertEquals(9 * 60, HomePreviewData.timelineBlocks.first().startMinute)
        assertTrue(HomePreviewData.timelineBlocks.first().isLocked)
        assertEquals("5m", HomePreviewData.unscheduledTasks[1].estimate)
    }
}
