package com.adrianbrady.fetchproject.data.api

import com.adrianbrady.fetchproject.data.model.ProjectItemApi
import retrofit2.Response
import retrofit2.http.GET

interface ProjectApiService {
    @GET("hiring.json")
    suspend fun getJSON(): Response<List<ProjectItemApi>>
}