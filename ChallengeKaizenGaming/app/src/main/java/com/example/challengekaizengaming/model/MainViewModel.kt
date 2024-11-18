package com.example.challengekaizengaming.model

import androidx.lifecycle.ViewModel
import com.example.challengekaizengaming.dto.ApiResponse
import com.example.challengekaizengaming.dto.SportDTO
import com.example.challengekaizengaming.manager.ServiceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    private val _sports = MutableStateFlow<List<SportDTO>?>(null)
    val sports : StateFlow<List<SportDTO>?> = _sports

     suspend fun getSports() {
         val response = ServiceManager.api.getSports()
         println(response)

         val sportDTOList: List<SportDTO> = response.flatMap { elem ->
             when (val d = elem.d) {
                 is String -> {
                     // Caso onde `d` é uma String, cria um único SportDTO
                     listOf(
                         SportDTO(
                             i = elem.i,
                             d = d,
                             e = elem.e ?: emptyList()
                         )
                     )
                 }
                 is List<*> -> {
                     d.filterIsInstance<SportDTO>().map { nestedSport ->
                         SportDTO(
                             i = nestedSport.i,
                             d = nestedSport.d as? String ?: "",
                             e = nestedSport.e
                         )
                     }
                 }
                 else -> emptyList()
             }
         }

         _sports.value = sportDTOList
     }

}