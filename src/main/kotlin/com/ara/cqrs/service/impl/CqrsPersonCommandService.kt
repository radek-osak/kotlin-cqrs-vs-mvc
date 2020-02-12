package com.ara.cqrs.service.impl

import com.ara.cqrs.repository.CqrsPersonRepository
import com.ara.cqrs.service.PersonCommandService
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.springframework.stereotype.Service

@Service
class CqrsPersonCommandService(private val personRepository: CqrsPersonRepository,
                               private val personQueryService: PersonQueryService) : PersonCommandService {

    override fun save(person: Person) {
        personRepository.save(person)
    }

    override fun delete(id: Long) {
        val person : Person = personQueryService.findById(id)
        personRepository.delete(person)
    }
}