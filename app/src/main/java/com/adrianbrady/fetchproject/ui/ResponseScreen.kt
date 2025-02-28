package com.adrianbrady.fetchproject.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.adrianbrady.fetchproject.R
import com.adrianbrady.fetchproject.data.model.ItemGroup
import com.adrianbrady.fetchproject.data.model.ProjectItem
import com.adrianbrady.fetchproject.ui.theme.FetchProjectTheme


@Composable
fun ItemGroups(
    projectUiState: List<ItemGroup>,
    contentPadding: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        items(projectUiState) { group ->
            Card(
                modifier = modifier.fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                GroupTitle(group)
                ItemGroup(group, modifier)
            }
        }
    }
}

@Composable
fun GroupTitle(
    group: ItemGroup,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = "listId: ${group.listId}",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ItemGroup(
    group: ItemGroup,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(R.dimen.padding_small))
            .padding(bottom = dimensionResource(R.dimen.padding_small))
    ) {
        group.items.forEach { item ->
            Text(
                text = "id: ${item.id}, name: ${item.name}",
                style = MaterialTheme.typography.bodyLarge
                //text = projectUiState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTitleBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row() {
                Text(
                    text = "Fetch Project",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun ItemGroupsPreview() {
    FetchProjectTheme {
        Surface {
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
}
@Preview
@Composable
fun ItemGroupsDarkPreview() {
    FetchProjectTheme(darkTheme = true) {
        Surface {
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
}
