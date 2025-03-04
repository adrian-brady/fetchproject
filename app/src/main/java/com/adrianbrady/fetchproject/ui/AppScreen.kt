package com.adrianbrady.fetchproject.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adrianbrady.fetchproject.R

/**
 * AppScreen is the main display of the app. It wraps the NavHost for Home and Request navigation.
 */
@Composable
fun AppScreen(
    viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )

    Scaffold(
        topBar = {
            AppTitleBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen(
                    onNextButtonClicked = {
                        navController.navigate(AppScreen.Results.name)
                        viewModel.makeRequest()
                    }
                )
            }
            composable(
                route = AppScreen.Results.name,
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                }
            ) {
                RequestScreen(viewModel.uiState, innerPadding)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTitleBar(
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

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )
    Scaffold(
        topBar = {
            AppTitleBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen(
                    onNextButtonClicked = {
                    }
                )
            }
        }
    }
}