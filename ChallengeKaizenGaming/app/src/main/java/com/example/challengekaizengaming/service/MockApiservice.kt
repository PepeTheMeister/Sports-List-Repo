package com.example.challengekaizengaming.service

import com.example.challengekaizengaming.dto.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface MockApiservice {

    @GET("sports")
    suspend fun getSports() : List<ApiResponse>
}