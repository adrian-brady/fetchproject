package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * The ProjectScreen displays the status of the data request. It has 3 states: Loading, Success, and Error.
 */
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
           is ProjectUiState.Success -> ResponseScreen(projectUiState.response, contentPadding, modifier)
           is ProjectUiState.Loading -> LoadingScreen(contentPadding, modifier)
           is ProjectUiState.Error -> ErrorScreen(contentPadding, modifier)
       }
   }
}

