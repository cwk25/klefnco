package com.klefnco.concert

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "seats")
class Seat(
    @ManyToOne
    @JoinColumn(name = "event_id")
    val concertEvent : ConcertEvent,

    @field:Column(name = "group_row")
    val groupRow: String,

    @field:Column(name = "group_column")
    val groupColumn: String,

    @field:Column(name = "row_number")
    val rowNumber: String,

    @field:Column(name = "seat_number")
    val seatNumber: String): Aggregate() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "available")
    var available: Boolean = true
        private set

    @Column(name = "booking_number")
    var bookingNumber: String? = null

    //TODO: create user and book with user (email and phone number)
    //TODO: on create, raise event to create ticket
    fun book(user: User): Result<Unit>{
        if (!available) {
            return Result.failure(SeatUnavailableException(rowNumber, seatNumber))
        }

        available = false
        domainEvents.add(SeatBooked(this, user))

        return Result.success(Unit)
    }
}