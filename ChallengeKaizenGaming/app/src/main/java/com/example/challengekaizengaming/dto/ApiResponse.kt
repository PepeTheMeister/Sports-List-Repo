package com.example.challengekaizengaming.dto

data class ApiResponse(
    val i: String,
    val d: Any,
    val e: List<EventDTO>? = null

) {
}