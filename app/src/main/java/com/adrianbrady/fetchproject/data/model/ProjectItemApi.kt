package com.adrianbrady.fetchproject.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectItemApi(
    val id: Int,
    val listId: Int,
    val name: String? = null,
)