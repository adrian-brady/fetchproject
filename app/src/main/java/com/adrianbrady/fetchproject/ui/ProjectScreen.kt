package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adrianbrady.fetchproject.data.model.Response

@Composable
fun ProjectScreen(
   projectUiState: String,
   modifier: Modifier = Modifier,
   contentPadding: PaddingValues = PaddingValues(0.dp)
) {
   Box(
      contentAlignment = Alignment.Center,
      modifier = modifier
   ) {
      Text(text = projectUiState)
   }
}