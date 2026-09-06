package com.hanhyo.plot.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanhyo.plot.core.ui.theme.PlotTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) { viewModel.handleIntent(HomeIntent.Refresh) }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = state.message)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PlotTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Hello, Plot!")
        }
    }
}
