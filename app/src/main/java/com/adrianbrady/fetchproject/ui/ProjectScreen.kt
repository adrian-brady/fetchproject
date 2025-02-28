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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.adrianbrady.fetchproject.R
import com.adrianbrady.fetchproject.data.model.ItemGroup
import com.adrianbrady.fetchproject.data.model.ProjectItem
import com.adrianbrady.fetchproject.ui.theme.FetchProjectTheme

@Composable
fun ProjectScreen(
   projectUiState: List<ItemGroup>,
   contentPadding: PaddingValues,
   modifier: Modifier = Modifier,
) {
   Box(
      contentAlignment = Alignment.Center,
      modifier = modifier
   ) {
      ItemGroups(projectUiState, contentPadding, modifier)
   }
}

