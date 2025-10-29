package com.klefnco.concert

import org.springframework.data.domain.DomainEvents

abstract class Aggregate{
    protected val domainEvents = mutableListOf<DomainEvent>()

    @DomainEvents
    fun domainEvents() = domainEvents
}