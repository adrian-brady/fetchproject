package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adrianbrady.fetchproject.R
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
      ItemGroups(projectUiState, modifier)
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
         Card(
               modifier = modifier.fillMaxWidth()
                  .padding(dimensionResource(R.dimen.padding_small))
         ) {
            Row(
               modifier = modifier
                   .padding(dimensionResource(R.dimen.padding_small))
            ) {
               Text(
                  text = "listId: ${group.listId}",
                  fontSize = 20.sp,
                  textAlign = TextAlign.Center
               )
            }
            Row(
               modifier = modifier
                   .padding(dimensionResource(R.dimen.padding_small))
            ) {
               ItemGroup(group, modifier)
            }
         }
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
fun ItemGroupsPreview(
   modifier: Modifier = Modifier
) {
   Surface(
      modifier = modifier
   ) {
      ItemGroups(listOf(
         ItemGroup(1,
            listOf(
               ProjectItem(1, "item 1"),
               ProjectItem(2, "item 2"),
            )),
         ItemGroup(2,
            listOf(
               ProjectItem(1, "item 1"),
               ProjectItem(2, "item 2"),
            )),
         ItemGroup(3,
            listOf(
               ProjectItem(1, "item 1"),
               ProjectItem(2, "item 2"),
            )),
         ItemGroup(4,
            listOf(
               ProjectItem(1, "item 1"),
               ProjectItem(2, "item 2"),
               )),
      ))
   }
}