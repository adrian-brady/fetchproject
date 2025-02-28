package com.adrianbrady.fetchproject.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProjectApp() {
    val projectViewModel: ProjectViewModel = viewModel(factory = ProjectViewModel.Factory)
    Scaffold(topBar = {ProjectTitleBar()}) { it ->
        Surface {
            ProjectScreen(
                projectUiState = projectViewModel.projectUiState,
                contentPadding = it,
            )
        }
    }
}