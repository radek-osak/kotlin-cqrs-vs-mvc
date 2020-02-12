package com.ara.cqrs.repository

import com.ara.model.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonQueryRepository: CrudRepository<Person, Long> {
}