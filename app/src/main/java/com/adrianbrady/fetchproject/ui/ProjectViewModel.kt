package com.adrianbrady.fetchproject.ui

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
import com.adrianbrady.fetchproject.data.ResponseRepository
import com.adrianbrady.fetchproject.data.model.ItemGroup
import java.io.IOException

sealed interface ProjectUiState {
    data class Success(val response: List<ItemGroup>) : ProjectUiState
    object Error : ProjectUiState
    object Loading : ProjectUiState
}

class ProjectViewModel(private val responseRepository: ResponseRepository) : ViewModel() {
    var projectUiState: ProjectUiState by mutableStateOf(ProjectUiState.Loading)
        private set

    init {
        getJSON()
    }

    private fun getJSON() {
        viewModelScope.launch {
            projectUiState = try {
                val result = responseRepository.getResponseJSON()
                ProjectUiState.Success(result)
            } catch (e: IOException) {
                ProjectUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ProjectApplication)
                val repository = application.container.responseRepository
                ProjectViewModel(repository)
            }
        }
    }
}