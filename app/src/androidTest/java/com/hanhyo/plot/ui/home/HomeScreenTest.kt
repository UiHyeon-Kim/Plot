package com.hanhyo.plot.ui.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hanhyo.plot.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun home_showsCanonicalTimelineAndFiveDestinations() {
        composeRule.onNodeWithText("오늘").assertIsDisplayed()
        composeRule.onNodeWithText("오늘 시간 예산").assertIsDisplayed()
        composeRule.onNodeWithText("팀 미팅").assertIsDisplayed()
        composeRule.onNodeWithText("보고서 작성").assertIsDisplayed()

        listOf("홈", "리스트", "캘린더", "매트릭스", "습관").forEach { label ->
            composeRule.onNodeWithTag("plot-nav-$label").assertIsDisplayed()
        }
        composeRule.onNodeWithTag("plot-nav-홈").assertIsSelected()
    }

    @Test
    fun profileButton_opensAndDismissesMenu() {
        composeRule.onNodeWithContentDescription("프로필").performClick()

        composeRule.onNodeWithText("김의현").assertIsDisplayed()
        composeRule.onNodeWithText("주간 리뷰").assertIsDisplayed()
        composeRule.onNodeWithText("목표 · 만다르트").assertIsDisplayed()

        composeRule.onNodeWithText("설정").performClick()
        composeRule.onAllNodesWithText("김의현").assertCountEquals(0)
    }
}
