package com.adrianbrady.fetchproject.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adrianbrady.fetchproject.R

@Composable
fun ProjectApp(
    viewModel: ProjectViewModel = viewModel(factory = ProjectViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )

    Scaffold(
        topBar = {
            ProjectTitleBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen(
                    onNextButtonClicked = {
                        viewModel.makeRequest()
                        navController.navigate(AppScreen.Results.name)
                    }
                )
            }
            composable(route = AppScreen.Results.name) {
                ProjectScreen(viewModel.projectUiState, innerPadding)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTitleBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Row() {
                Text(
                    text = "Fetch Project",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

