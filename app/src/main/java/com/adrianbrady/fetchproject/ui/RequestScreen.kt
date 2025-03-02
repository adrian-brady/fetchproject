package com.adrianbrady.fetchproject.ui

import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimatedVectorDrawable
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
import androidx.compose.material.icons.filled.ExpandLess
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
import com.adrianbrady.fetchproject.data.model.ItemList
import com.adrianbrady.fetchproject.data.model.Item
import com.adrianbrady.fetchproject.ui.theme.FetchProjectTheme

/**
 * The RequestScreen displays the status of the data request. It has 3 states: Loading, Success, and Error.
 */
@Composable
fun RequestScreen(
    uiState: UiState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        when (uiState) {
            is UiState.Success -> RequestSuccessScreen(uiState.response, modifier)
            is UiState.Loading -> RequestLoadingScreen(contentPadding, modifier)
            is UiState.Error -> RequestErrorScreen(contentPadding, uiState.errorMessage, modifier)
        }
    }
}

@Composable
fun RequestSuccessScreen(
    projectUiState: List<ItemList>,
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
                ListTitle(
                    group,
                    expanded,
                    onClick = { expanded = !expanded },
                    modifier,
                )
                if (expanded) {
                    ListContent(group, modifier)
                }
            }
        }
    }
}

@Composable
fun ListTitle(
    group: ItemList,
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
                imageVector = if (expanded) Icons.Filled.ExpandMore else Icons.Filled.ExpandLess,
                contentDescription = stringResource(R.string.expand_button_content_description),
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
fun ListContent(
    group: ItemList,
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
fun RequestLoadingScreen(
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
fun RequestErrorScreen(
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
            RequestSuccessScreen(listOf(
                ItemList(1,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
                ItemList(2,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
                ItemList(3,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
                ItemList(4,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
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
            RequestSuccessScreen(listOf(
                ItemList(1,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
                ItemList(2,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
                ItemList(3,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
                ItemList(4,
                    listOf(
                        Item(1, "item 1"),
                        Item(2, "item 2"),
                    )),
            ))
        }

    }
}
