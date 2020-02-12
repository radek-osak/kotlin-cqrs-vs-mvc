package com.ara.cqrs.service

import com.ara.model.Person
import org.springframework.stereotype.Service

@Service
interface PersonCommandService {

    fun save(person: Person)
    fun delete(id: Long)
}