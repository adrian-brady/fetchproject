package com.adrianbrady.fetchproject.data

import com.adrianbrady.fetchproject.data.api.ProjectApiService

interface AppContainer {
    val responseRepository: ResponseRepository
}

class MockService() : ProjectApiService {
    override suspend fun getJSON(): String {
        return "Mock Response"
    }
}

class DefaultAppContainer : AppContainer {
    private val service: ProjectApiService = MockService()
    override val responseRepository: ResponseRepository by lazy {
        NetworkResponseRepository(service)
    }
}