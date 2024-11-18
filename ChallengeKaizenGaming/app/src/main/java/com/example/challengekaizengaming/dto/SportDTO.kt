package com.example.challengekaizengaming.dto


data class SportDTO(
    val i : String,
    val d : String,
    val e : List<EventDTO>,
    var isExpanded : Boolean = false,
    var isFiltered : Boolean = false
)
