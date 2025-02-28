package com.adrianbrady.fetchproject.data

import com.adrianbrady.fetchproject.data.api.ProjectApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val responseRepository: ResponseRepository
}

class DefaultAppContainer : AppContainer {
    private val baseURL = "https://fetch-hiring.s3.amazonaws.com"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()

    private val retrofitService: ProjectApiService by lazy {
        retrofit.create(ProjectApiService::class.java)
    }

    override val responseRepository: ResponseRepository by lazy {
        NetworkResponseRepository(retrofitService)
    }
}