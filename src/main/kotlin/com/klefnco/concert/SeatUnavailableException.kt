package com.klefnco.concert

class SeatUnavailableException(val rowNumber: String, val seatNumber: String):
    RuntimeException("Seat '$rowNumber $seatNumber' is unavailable.")