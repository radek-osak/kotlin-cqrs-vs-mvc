package com.ara.cqrs.service

import com.ara.model.Person
import org.springframework.stereotype.Service

@Service
interface PersonQueryService {

    fun findAll(): MutableIterable<Person>
    fun findByName(name: String) : Person
}