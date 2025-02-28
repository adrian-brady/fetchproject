package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adrianbrady.fetchproject.data.model.ProjectData

@Composable
fun ProjectScreen(
   projectUiState: List<ProjectData>,
   modifier: Modifier = Modifier,
   contentPadding: PaddingValues = PaddingValues(0.dp)
) {
   Box(
      contentAlignment = Alignment.Center,
      modifier = modifier
   ) {
      LazyColumn {
         items(projectUiState) { data ->
            Text(
               text = "id: ${data.id}, listId: ${data.listId}, name: ${data.name}"
               //text = projectUiState
            )
         }
      }
   }
}