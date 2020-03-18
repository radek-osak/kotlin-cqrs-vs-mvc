package com.ara.cqrs.repository

import com.ara.model.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonQueryRepository: CrudRepository<Person, Long> {

    fun findByName(name : String) : Optional<Person>
}