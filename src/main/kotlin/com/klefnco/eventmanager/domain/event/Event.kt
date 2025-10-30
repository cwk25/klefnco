package com.klefnco.eventmanager.domain.event

import com.klefnco.eventmanager.domain.seat.Seat
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "concert_events")
class Event(val name: String, val dateTime: LocalDateTime){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @OneToMany(mappedBy = "event", cascade = [(CascadeType.REMOVE)], orphanRemoval = true)
    val tickets: MutableList<Seat> = mutableListOf()
}