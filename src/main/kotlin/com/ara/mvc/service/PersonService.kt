package com.ara.mvc.service

import com.ara.mvc.model.Person
import org.springframework.stereotype.Service

@Service
interface PersonService {

    fun findAll(): MutableIterable<Person>
    fun findById(id: Long) : Person
    fun save(person: Person)
    fun delete(id: Long)
}