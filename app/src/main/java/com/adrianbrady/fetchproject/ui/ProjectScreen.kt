package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrianbrady.fetchproject.data.model.ItemGroup
import com.adrianbrady.fetchproject.data.model.ProjectItem
import com.adrianbrady.fetchproject.data.model.ProjectItemApi

@Composable
fun ProjectScreen(
   projectUiState: List<ItemGroup>,
   modifier: Modifier = Modifier,
   contentPadding: PaddingValues = PaddingValues(0.dp)
) {
   Box(
      contentAlignment = Alignment.Center,
      modifier = modifier
   ) {
      ItemGroups(projectUiState)
   }
}

@Composable
fun ItemGroups(
   projectUiState: List<ItemGroup>,
   modifier: Modifier = Modifier,
) {
   LazyColumn(
      modifier = modifier
   ) {
      items(projectUiState) { group ->
         Text(
            text = "${group.listId}",
            fontSize = 20.sp,
         )
         ItemGroup(group)
      }
   }
}

@Composable
fun ItemGroup(
   group: ItemGroup,
   modifier: Modifier = Modifier
){
   Column(
      modifier = modifier
   ) {
      group.items.forEach { item ->
         Text(
            text = "id: ${item.id}, name: ${item.name}"
            //text = projectUiState
         )
      }
   }
}

@Preview
@Composable
fun ItemGroupsPreview() {
   Surface(
      modifier = Modifier.fillMaxWidth()
   ) {
      ItemGroups(listOf(
         ItemGroup(1, listOf(ProjectItem(1, "item 1"))),
         ItemGroup(2, listOf(ProjectItem(1, "item 1"))),
         ItemGroup(3, listOf(ProjectItem(1, "item 1"))),
         ItemGroup(4, listOf(ProjectItem(1, "item 1"))),
      ))
   }
}