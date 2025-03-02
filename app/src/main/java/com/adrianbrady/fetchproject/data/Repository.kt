package com.adrianbrady.fetchproject.data

import android.util.Log
import com.adrianbrady.fetchproject.data.api.ApiService
import com.adrianbrady.fetchproject.data.model.ItemList
import com.adrianbrady.fetchproject.data.model.Item

interface Repository {
    suspend fun getResponseJSON(): List<ItemList>
}

private const val TAG = "NETWORK_REPOSITORY"

class NetworkRepository(
    private val apiService: ApiService
) : Repository {
    override suspend fun getResponseJSON(): List<ItemList> {
        val response = apiService.getJSON()
        Log.d(TAG, "response received")

        if (response.isSuccessful) {
            val items = response.body() ?: emptyList()
            // Parse list of ProjectItemApi items into a list of ItemGroups, grouped by their listId
            val groups = items
                .groupBy { it.listId }
                .map{ ItemList(
                    it.key,
                    items = it.value
                        .mapNotNull { item -> item.name?.let { Item(item.id, it) } }
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