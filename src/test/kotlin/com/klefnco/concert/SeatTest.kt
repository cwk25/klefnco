package com.klefnco.concert

import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.samePropertyValuesAs
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class SeatTest {
    @Test
    fun shouldBookSuccessfullyWhenTicketIsAvailable(){
        val concertEvent = concertEvent()
        val seat = Seat(concertEvent, "-", "-", "A", "1")

        val actual = seat.book()

        assertThat(actual).isEqualTo(Result.success(Unit))
        assertThat(seat.available).isEqualTo(false)
    }

    @Test
    fun shouldBookFailureWhenSeatIsNotAvailable(){
        val concertEvent = concertEvent()
        val seat = Seat(concertEvent, "-", "-", "A", "1")
        seat.book()

        val expected = Result.failure<SeatUnavailableException>(SeatUnavailableException("A", "1"))

        val actual = seat.book()

        MatcherAssert.assertThat(actual, samePropertyValuesAs(expected))
    }

    private fun concertEvent(): ConcertEvent {
        return ConcertEvent("Concert One", LocalDateTime.now())
    }
}