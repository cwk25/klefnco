package com.klefnco.concert

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TicketTest {
    @Test
    fun shouldBookSuccessfullyWhenTicketIsAvailable(){
        val concertEvent = FakeConcertEventFactory.create()
        val ticket = Seat(concertEvent, )
    }
}