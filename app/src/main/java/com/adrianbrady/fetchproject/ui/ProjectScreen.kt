package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProjectScreen(
   projectUiState: ProjectUiState,
   contentPadding: PaddingValues,
   modifier: Modifier = Modifier,
) {
   Box(
      contentAlignment = Alignment.Center,
      modifier = modifier
   ) {
       when (projectUiState) {
           is ProjectUiState.Success -> ItemResponseScreen(projectUiState.response, contentPadding, modifier)
           is ProjectUiState.Loading -> ItemLoadingScreen(contentPadding, modifier)
           is ProjectUiState.Error -> ErrorScreen(contentPadding, modifier)
       }
   }
}

