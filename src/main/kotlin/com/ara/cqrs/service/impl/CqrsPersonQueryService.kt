package com.ara.cqrs.service.impl

import com.ara.cqrs.repository.CqrsPersonRepository
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class CqrsPersonQueryService(private val personRepository: CqrsPersonRepository) : PersonQueryService {

    override fun findAll(): MutableIterable<Person> {
        return personRepository.findAll()
    }

    override fun findById(id: Long): Person {
        var result : Optional<Person> = personRepository.findById(id)
        if (result.isPresent)
            return result.get()
        else
            throw RuntimeException("Can not find person with id ${id}")
    }
}