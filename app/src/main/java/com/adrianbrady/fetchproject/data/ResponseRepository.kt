package com.adrianbrady.fetchproject.data

import com.adrianbrady.fetchproject.data.api.ProjectApiService
import com.adrianbrady.fetchproject.data.model.ItemGroup
import com.adrianbrady.fetchproject.data.model.ProjectItem

interface ResponseRepository {
    suspend fun getResponseJSON(): List<ItemGroup>
}

class NetworkResponseRepository(
    private val apiService: ProjectApiService
) : ResponseRepository {
    override suspend fun getResponseJSON(): List<ItemGroup> {
        //val response = """[{"id": 1, "listId": 1, "name": null},{"id": 1, "listId": 1, "name": null}]"""
        //val json = Json.decodeFromString<List<ProjectData>>(response)
        //return json
        val response = apiService.getJSON()
        if (response.isSuccessful) {
            val items = response.body() ?: emptyList()
            val groups = items
                .groupBy { it.listId }
                .map{ ItemGroup(
                    it.key,
                    items = it.value
                        .mapNotNull { item -> item.name?.let { ProjectItem(item.id, it) } }
                        .filter { it.name != "" }
                        .sortedBy { it.id }
                ) }
                .sortedBy { it.listId }
            return groups
        } else {
            throw Exception("network request error")
        }
    }
}