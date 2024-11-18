package com.example.challengekaizengaming

import com.example.challengekaizengaming.adapter.SportAdapter
import com.example.challengekaizengaming.dto.EventDTO
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun filteredEvents() {

        val events = listOf(
            EventDTO("1", "Sport 1", "Team A - Team B", 0, "Team A - Team B", isFavorite = true),
            EventDTO("2", "Sport 2", "Team C - Team D", 0, "Team C - Team D", isFavorite = false),
            EventDTO("3", "Sport 3", "Team E - Team F", 0, "Team E - Team F", isFavorite = true)
        )


        val adapter = SportAdapter(emptyList())
        val filteredEvents = adapter.getFilteredEvents(events)

        assertEquals(2, filteredEvents.size)
        assertEquals("1", filteredEvents[0].i)
        assertEquals("3", filteredEvents[1].i)
    }
}