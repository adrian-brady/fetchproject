package com.adrianbrady.fetchproject.data

import android.util.Log
import com.adrianbrady.fetchproject.data.api.ProjectApiService
import com.adrianbrady.fetchproject.data.model.ProjectData
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

interface ResponseRepository {
    suspend fun getResponseJSON(): List<ProjectData>
}

class NetworkResponseRepository(
    private val apiService: ProjectApiService
) : ResponseRepository {
    override suspend fun getResponseJSON(): List<ProjectData> {
        //val response = """[{"id": 1, "listId": 1, "name": null},{"id": 1, "listId": 1, "name": null}]"""
        //val json = Json.decodeFromString<List<ProjectData>>(response)
        //return json
        val response = apiService.getJSON()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("network request error")
        }
    }
}