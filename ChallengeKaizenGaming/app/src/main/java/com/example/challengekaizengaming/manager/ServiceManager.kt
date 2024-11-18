package com.example.challengekaizengaming.manager


import com.example.challengekaizengaming.service.MockApiservice
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceManager {

    //private const val URL = "https://618d3aa7fe09aa001744060a.mockapi.io/api/"
    private const val URL = "https://kaizen.free.beeceptor.com/"

    val api: MockApiservice = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MockApiservice::class.java)

}

