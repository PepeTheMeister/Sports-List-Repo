package com.example.challengekaizengaming.model

import androidx.lifecycle.ViewModel
import com.example.challengekaizengaming.dto.SportDTO
import com.example.challengekaizengaming.manager.ServiceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel(){

    private val _sports = MutableStateFlow<MutableList<SportDTO>?>(null)
    val sports : StateFlow<MutableList<SportDTO>?> = _sports

    suspend fun getSports() {
        val response = ServiceManager.api.getSports()
        _sports.value = response
    }

}