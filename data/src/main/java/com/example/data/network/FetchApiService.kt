package com.example.data.network

import com.example.data.network.models.ApiListItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchApiService {
    @GET("hiring.json")
    suspend fun getList(): Response<List<ApiListItem?>?>
}