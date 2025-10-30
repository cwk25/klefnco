package com.klefnco.eventmanager.domain.seat

class SeatUnavailableException(val rowNumber: String, val seatNumber: String):
    RuntimeException("Seat '$rowNumber $seatNumber' is unavailable.")