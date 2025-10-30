package com.klefnco.eventmanager.domain

import com.klefnco.eventmanager.domain.event.Event
import com.klefnco.eventmanager.domain.seat.Seat
import com.klefnco.eventmanager.domain.seat.SeatUnavailableException
import com.klefnco.eventmanager.domain.user.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class SeatTest {
    @Test
    fun shouldBookSuccessfullyWhenTicketIsAvailable(){
        val event = createEvent()
        val seat = Seat(event, "-", "-", "A", "1")
        val user = User("user1", "user1@mail.com", "123456789")

        val actual = seat.book(user)
        Assertions.assertThat(actual).isEqualTo(Result.success(Unit))
        Assertions.assertThat(seat.available).isEqualTo(false)
    }

    @Test
    fun shouldBookFailureWhenSeatIsNotAvailable(){
        val event = createEvent()
        val seat = Seat(event, "-", "-", "A", "1")
        val user = User("user1", "user1@mail.com", "123456789")

        seat.book(user)

        val expected = Result.failure<SeatUnavailableException>(SeatUnavailableException("A", "1"))

        val actual = seat.book(user)

        Assertions.assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expected)
    }

    private fun createEvent(): Event {
        return Event("Concert One", LocalDateTime.now())
    }
}