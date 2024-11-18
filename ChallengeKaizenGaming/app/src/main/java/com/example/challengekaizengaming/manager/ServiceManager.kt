package com.example.challengekaizengaming.manager


import com.example.challengekaizengaming.dto.ApiResponse
import com.example.challengekaizengaming.dto.SportDTO
import com.example.challengekaizengaming.service.MockApiservice
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceManager {

    private const val URL = "https://618d3aa7fe09aa001744060a.mockapi.io/api/"
    //private const val URL = "https://kaizen.free.beeceptor.com/"

    private val serializer: Gson = GsonBuilder()
        .registerTypeAdapter(
            Any::class.java, ServiceAdapter()
        )
        .create()


    val api: MockApiservice = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(serializer))
        .build()
        .create(MockApiservice::class.java)


}

