package com.klefnco.eventmanager.domain.seat

import com.klefnco.eventmanager.domain.event.Event
import com.klefnco.eventmanager.domain.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.domain.AbstractAggregateRoot

@Entity
@Table(name = "seats")
class Seat(
    @ManyToOne
    @JoinColumn(name = "event_id")
    val event: Event,

    @field:Column(name = "group_row")
    val groupRow: String,

    @field:Column(name = "group_column")
    val groupColumn: String,

    @field:Column(name = "row_number")
    val rowNumber: String,

    @field:Column(name = "seat_number")
    val seatNumber: String): AbstractAggregateRoot<Seat>() {

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

        registerEvent(SeatBooked(this, user))

        return Result.success(Unit)
    }
}