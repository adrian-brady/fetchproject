package com.adrianbrady.fetchproject.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.adrianbrady.fetchproject.ProjectApplication
import com.adrianbrady.fetchproject.data.Repository
import com.adrianbrady.fetchproject.data.model.ItemList
import java.io.IOException

/**
 * Holds the UI State of the app, wraps the parsed response of the GET request to the URL on the Success Case.
 */
sealed interface UiState {
    data class Success(val response: List<ItemList>) : UiState
    data class Error(val errorMessage: String) : UiState
    object Loading : UiState
}

private const val TAG = "PROJECT_VIEW_MODEL"

class AppViewModel(private val repository: Repository) : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    private fun getJSON() {
        Log.d(TAG, "getJSON Called")
        if (uiState is UiState.Loading || uiState is UiState.Error) {
            viewModelScope.launch {
                uiState = try {
                    val result = repository.getResponseJSON()
                    UiState.Success(result)
                } catch (e: IOException) {
                    UiState.Error(e.message ?: "")
                }
            }
        }
    }

    /**
     * Public caller for requesting the JSON from the URL.
     * Handles setting the UI state when a request is made.
     */
    fun makeRequest() {
        uiState = UiState.Loading
        getJSON()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ProjectApplication)
                val repository = application.container.repository
                AppViewModel(repository)
            }
        }
    }
}