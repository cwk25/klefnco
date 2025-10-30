package com.klefnco.eventmanager.domain.ticket

import com.klefnco.eventmanager.domain.user.User
import com.klefnco.eventmanager.domain.seat.Seat
import org.springframework.data.domain.AbstractAggregateRoot
import java.time.LocalDateTime
import java.util.UUID

class Ticket: AbstractAggregateRoot<Ticket> {
    var id: UUID = UUID.randomUUID()
    val createdAt = LocalDateTime.now()

    constructor(seat: Seat, user: User) {
        registerEvent(TicketCreated(this, user))
    }
}