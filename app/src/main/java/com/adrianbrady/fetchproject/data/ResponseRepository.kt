package com.adrianbrady.fetchproject.data

import android.util.Log
import com.adrianbrady.fetchproject.data.api.ProjectApiService
import com.adrianbrady.fetchproject.data.model.ItemGroup
import com.adrianbrady.fetchproject.data.model.ProjectItem

interface ResponseRepository {
    suspend fun getResponseJSON(): List<ItemGroup>
}

private const val TAG = "NETWORK_RESPONSE_REPOSITORY"

class NetworkResponseRepository(
    private val apiService: ProjectApiService
) : ResponseRepository {
    override suspend fun getResponseJSON(): List<ItemGroup> {
        val response = apiService.getJSON()
        Log.d(TAG, "response received")

        if (response.isSuccessful) {
            val items = response.body() ?: emptyList()
            // Parse list of ProjectItemApi items into a list of ItemGroups, grouped by their listId
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
            throw Exception("network request error ${response.errorBody()}")
        }
    }
}