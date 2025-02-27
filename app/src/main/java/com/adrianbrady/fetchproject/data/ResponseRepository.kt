package com.adrianbrady.fetchproject.data

import com.adrianbrady.fetchproject.data.api.ProjectApiService

interface ResponseRepository {
    suspend fun getResponseJSON(): String
}

class NetworkResponseRepository(
    private val apiService: ProjectApiService
) : ResponseRepository {
    override suspend fun getResponseJSON(): String = apiService.getJSON()
}