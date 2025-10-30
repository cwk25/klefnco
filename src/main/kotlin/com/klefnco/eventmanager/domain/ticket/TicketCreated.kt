package com.klefnco.eventmanager.domain.ticket

import com.klefnco.eventmanager.domain.user.User

class TicketCreated(val ticket: Ticket, val user: User)