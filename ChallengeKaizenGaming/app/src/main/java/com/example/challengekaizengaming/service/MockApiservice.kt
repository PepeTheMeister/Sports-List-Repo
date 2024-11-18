package com.example.challengekaizengaming.service

import com.example.challengekaizengaming.dto.SportDTO
import retrofit2.http.GET

interface MockApiservice {

    @GET("sports")
    suspend fun getSports() : MutableList<SportDTO>
}