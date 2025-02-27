package com.adrianbrady.fetchproject.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProjectApp() {
    Surface {
        val projectViewModel: ProjectViewModel = viewModel(factory = ProjectViewModel.Factory)
        ProjectScreen(
            projectUiState = projectViewModel.projectUiState,
        )
    }
}