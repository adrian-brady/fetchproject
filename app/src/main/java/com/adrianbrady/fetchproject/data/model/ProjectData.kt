package com.adrianbrady.fetchproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectData(
    val id: Int,
    val listId: Int,
    val name: String? = null,
)