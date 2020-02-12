package com.ara.cqrs.service.impl

import com.ara.cqrs.repository.PersonQueryRepository
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class DefaultPersonQueryService(private val personQueryRepository: PersonQueryRepository) : PersonQueryService {

    override fun findAll(): MutableIterable<Person> {
        return personQueryRepository.findAll()
    }

    override fun findById(id: Long): Person {
        var result : Optional<Person> = personQueryRepository.findById(id)
        if (result.isPresent)
            return result.get()
        else
            throw RuntimeException("Can not find person with id ${id}")
    }
}