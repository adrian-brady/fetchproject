package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class AppScreen() {
    Home,
    Results,
}

@Composable
fun HomeScreen(
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = modifier
        ) {
            RequestButton(onClick = onNextButtonClicked, modifier = modifier)
        }
    }
}

@Composable
fun RequestButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .widthIn(min = 25.dp)
    ) {
        Text("Request Data")
    }
}

@Preview
@Composable
fun ButtonPreview() {
    RequestButton(onClick = {})
}