package com.adrianbrady.fetchproject.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.adrianbrady.fetchproject.R
import com.adrianbrady.fetchproject.data.model.ItemGroup
import com.adrianbrady.fetchproject.data.model.ProjectItem
import com.adrianbrady.fetchproject.ui.theme.FetchProjectTheme

@Composable
fun ResponseScreen(
    projectUiState: List<ItemGroup>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        items(projectUiState) { group ->
            var expanded by remember { mutableStateOf(false) }
            Card(
                modifier = modifier.fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                GroupTitle(
                    group,
                    expanded,
                    onClick = { expanded = !expanded },
                    modifier,
                )
                if (expanded) {
                    GroupContent(group, modifier)
                }
            }
        }
    }
}

@Composable
fun GroupTitle(
    group: ItemGroup,
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = "List ${group.listId}",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick =  onClick,
        ) {
            Icon(
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = stringResource(R.string.expand_button_content_description),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun GroupContent(
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
                    text = item.name,
                    style = MaterialTheme.typography.bodyLarge
                )
        }
    }
}

@Composable
fun LoadingScreen(
    contentPadding: PaddingValues = PaddingValues(),
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize()
    ) {
        Text(
            text = "Loading",
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Composable
fun ErrorScreen(
    contentPadding: PaddingValues = PaddingValues(),
    message: String = "Error",
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.displayMedium
        )
    }
}

@Preview
@Composable
fun ItemGroupsPreview() {
    FetchProjectTheme {
        Surface {
            ResponseScreen(listOf(
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
            ResponseScreen(listOf(
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
