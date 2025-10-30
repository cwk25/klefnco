package com.klefnco.eventmanager.infrastructure

import com.klefnco.eventmanager.domain.seat.Seat
import org.springframework.data.repository.CrudRepository

interface SeatRepository: CrudRepository<Seat, Int> {
    fun findByRowNumberAndSeatNumber(rowNumber: String, seatNumber: String): Seat?
}