package com.adrianbrady.fetchproject.data.api

interface ProjectApiService {
    suspend fun getJSON(): String
}