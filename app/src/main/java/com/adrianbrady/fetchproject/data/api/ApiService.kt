package com.adrianbrady.fetchproject.data.api

import com.adrianbrady.fetchproject.data.model.ItemApi
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getJSON(): Response<List<ItemApi>>
}