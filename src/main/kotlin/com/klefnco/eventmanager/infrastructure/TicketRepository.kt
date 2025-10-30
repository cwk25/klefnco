package com.klefnco.eventmanager.infrastructure

import com.klefnco.eventmanager.domain.ticket.Ticket
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TicketRepository: CrudRepository<Ticket, UUID> {
}