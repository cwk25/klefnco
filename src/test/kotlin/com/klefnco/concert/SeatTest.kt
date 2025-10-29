package com.klefnco.concert

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class SeatTest {
    @Test
    fun shouldBookSuccessfullyWhenTicketIsAvailable(){
        val concertEvent = concertEvent()
        val seat = Seat(concertEvent, "-", "-", "A", "1")
        val user = User("user1", "user1@mail.com", "123456789")

        val actual = seat.book(user)

        assertThat(actual).isEqualTo(Result.success(Unit))
        assertThat(seat.available).isEqualTo(false)
        assertThat(seat.domainEvents())
            .usingRecursiveFieldByFieldElementComparator()
            .contains(SeatBooked(seat, user))
    }

    @Test
    fun shouldBookFailureWhenSeatIsNotAvailable(){
        val concertEvent = concertEvent()
        val seat = Seat(concertEvent, "-", "-", "A", "1")
        val user = User("user1", "user1@mail.com", "123456789")

        seat.book(user)

        val expected = Result.failure<SeatUnavailableException>(SeatUnavailableException("A", "1"))

        val actual = seat.book(user)

        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expected)
    }

    private fun concertEvent(): ConcertEvent {
        return ConcertEvent("Concert One", LocalDateTime.now())
    }
}